package com.yuan.bbs;

import com.yuan.bbs.common.utils.JwtUtil;
import com.yuan.bbs.service.UserService;
import com.yuan.bbs.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 由于前后端分离没有session，只有token，每次有token的请求都会被拦截下来去数据库查出数据
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Value("${jwt.header}")
    private String header;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        String token = request.getHeader(header);
        if (token != null) {
            String uid = jwtUtil.getSubject(token);
            System.out.println("uid:" + uid);
            System.out.println("doFilterInternal start:filter timing ms:" + (System.currentTimeMillis() - start));
            if (uid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(uid);
                // 这里大概会浪费5ms的时间从数据库获取数据,想到的优化：可以从登录返回token起，就把角色用户名放到token中
                // 然后解析token拿到这两个用来，要是要弃用token就难搞。所以可能还需要用到redis来帮一手
                // 或者返回的类型可以改简单点
                System.out.println("doFilterInternal user:" + userDetails.getUsername());
                SecurityUtil.setUserToSecurity(userDetails);
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("doFilterInternal end:jwt filter total [%d] ms with %s\n", (end - start), request.getRequestURI());
        chain.doFilter(request, response);
    }
}
