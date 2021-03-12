package com.yuan.bbs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuan.bbs.common.enums.ResultCode;
import com.yuan.bbs.common.lang.Result;
import com.yuan.bbs.common.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.security.PermitAll;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 参考：https://blog.csdn.net/WiLL_XS/article/details/104894724/
 * https://blog.csdn.net/zhong_csdn/article/details/79447185
 * 其实只需要，自定义过滤器类，401，403错误处理类 可以使用默认的
 * 然后在自定义过滤器类中  SecurityContext存放的Authentication对象（WxAppletAuthenticationToken类）也可以使用默认的
 * 这样Spring Security在springboot中的配置就很少很少了。只要2个类足以.“不对,还要有个jwt相关工具列”
 * **当访问的路径不在这个类的出现，而且没有携带token或者token无效 请求会被拦截**
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${permits}")
    private String[] permits;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 配置不需要认证（不用携带token）的路径、添加过滤器，禁用session
     * 暂未添加**鉴权**功能
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        addSpecifiedUrlsToPermits();
        System.out.println(Arrays.toString(permits));
        http
                .cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()   // 添加需要放行的路径↓,其他的路径就需要认证或者鉴定权限
                .authorizeRequests()
                .antMatchers(permits).permitAll()
                // url表达式校验权限
                // .antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()

                .and()   // 配置被拦截时的处理
                .exceptionHandling()
                // ↓ 在用户没有登录时用于引导用户进行登录认证 （返回HTTP:code401-未授权）
                .authenticationEntryPoint((req, resp, e) -> {
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("application/json");
                    resp.setStatus(401);
                    PrintWriter out = resp.getWriter();
                    // token无效或者没有携带token
                    out.println(objectMapper.writeValueAsString(Result.fail(ResultCode.NO_VALID_TOKEN)));
                    out.flush();
                    out.close();
                })
                // ↓ 用于在用户已经登录了，但是访问了其自身没有权限的资源时做出对应的处理。（返回HTTP:code403-授权不足）
                .accessDeniedHandler((req, resp, e) -> {
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("application/json");
                    resp.setStatus(403);
                    PrintWriter out = resp.getWriter();
                    // 无权限 权限不足时的处理
                    out.println(objectMapper.writeValueAsString(Result.fail(ResultCode.PERMISSION_DENIED)));
                    out.flush();
                    out.close();
                })

                .and() // 添加自定义jwt过滤器
                .addFilterBefore(this.jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 用于放行静态资源，可以直接不走 Spring Security 过滤器链
     * 不对 还是触发了jwt认证token过滤器了。。
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/webjars/**", "/doc.html", "/v2/**", "/archive/**", "/swagger-resources/**") // knife4j相关放行
                .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**", "/index.html", "/favicon.ico");
    }

    /**
     * 获取所有带有@PermitAll的Controller的方法头上@GetMapping等里的Url并放入不被拦截的集合
     */
    private void addSpecifiedUrlsToPermits() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods =
                SpringUtil.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        List<String> list = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            PermitAll anonymousAccess = handlerMethod.getMethodAnnotation(PermitAll.class);
            if (anonymousAccess != null) {
                list.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            }
        }

        list.addAll(Arrays.asList(permits));
        permits = list.toArray(permits);
    }

}
