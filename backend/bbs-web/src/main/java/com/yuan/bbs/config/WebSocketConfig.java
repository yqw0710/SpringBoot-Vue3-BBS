package com.yuan.bbs.config;

import com.yuan.bbs.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * https://juejin.im/post/6844903908939137037
 * 1. 第一步编写配置类
 * 2. 编写消息模型用来交互
 * 3. 编写控制器处理用户的请求
 * 4. 编写监听器监听用户连接与断开
 * 5. 前端使用stomp.js Client连接
 * 6. 对stomp和按钮绑定相应事件
 * <p>
 * 还没学如何根据token进行拦截,这个demo网上复制下来逐行学习的.所以就都放一起了
 * https://blog.csdn.net/ch999999999999999999/article/details/102635322/
 * <p>
 * https://www.cnblogs.com/myitroad/p/9334141.html
 */
@Configuration
@EnableWebSocketMessageBroker
//@Order(Ordered.HIGHEST_PRECEDENCE)//标记的自定义拦截器来完成
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 注册端点
     * <p>
     * /ws是 WebSocket(或 SockJS)客户端为 WebSocket 握手需要连接的端点的 HTTP URL。
     * 例:ws://localhost:8080/ws
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*/*")
                .addInterceptors(new StompConnectInterceptor());
    }

    /**
     * 配置消息代理
     * 1. 启用简单的代理。代理/topic 进行广播  /queue 进行信息交换
     * ----topic和/queue前缀没有任何特殊含义。它们只是区分 pub-sub 与 point-to-point 消息传递的惯例(即许多订阅者与一个消费者)
     * 2. 设置应用端点前缀
     * ----目标头以/app开头的 STOMP 消息将路由到控制器类中的@MessageMapping方法。而不会发布到代理队列或主题中。
     * 3. 设置/user2为用户点对点发送消息的订阅前缀 如 stomp.subscribe("/user2/queue/shouts",()=>{}) [[默认是user]]
     * ----当用户B用上面的js代码订阅之后，用户A要发送消息给B可以先发送到带有@MessageMapping的方法(带上接收者)，
     * ----然后用SimpMessageSendingOperations.convertAndSendToUser(接收人，目的地`/queue/shouts`,消息)发送消息
     * ----然后订阅了这个目的地的用户B就能收到来自A的消息
     * 4. 设置点作为分隔符，之后控制器可以使用点(.)作为@MessageMapping方法中的分隔符 例子如下
     * ----@MessageMapping("blue.{green}")
     * ----public void handleGreen(@DestinationVariable String green) { //.. }
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setPathMatcher(new AntPathMatcher("."))
                .setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/topic", "/queue");
//        config.setUserDestinationPrefix("/user2/");
    }

    /**
     * 自定义建立websocket连接前的拦截器
     * 验证失败返回false拒绝本次连接
     */
    class StompConnectInterceptor extends HttpSessionHandshakeInterceptor {
        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String token = servletRequest.getParameter("token");
            try {
                jwtUtil.getSubject(token);
            } catch (Exception e) {
                return false;
            }
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }
    }

    /**
     * 设置拦截器：是连接后，对发送/接收消息前/后进行拦截处理，最下面这两个暂时没啥用
     * 1、首次连接的时候，获取其Header信息，利用Header里面的信息进行权限认证
     * 2、通过认证的用户，使用 accessor.setUser(user); 方法，将登陆信息绑定在该 StompHeaderAccessor 上，在Controller方法上可以获取 StompHeaderAccessor 的相关信息
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new StompChannelInterceptor());
    }

    /**
     * 对发送消息/接受前后进行拦截处理、(拦截个沟8)
     * 可以查看和/或修改从MessageChannel发送和/或接收的消息。
     */
    class StompChannelInterceptor implements ChannelInterceptor {

        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
            StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
            //1、判断是否首次连接,然后存储信息到Principal
            if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                String uid = jwtUtil.getSubject(accessor.getNativeHeader("token").get(0));
                // 这个类可以直接作为Controller的一个参数
                Principal principal = () -> uid;
                accessor.setUser(principal);
            }
            return message;
        }
    }

}

