package com.yuan.bbs.util;

import com.yuan.bbs.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    /**
     * 从SecurityContext获取被jwtFilter处理后存放的User对象
     */
    public static User getUserFromSecurity() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void setUserToSecurity(Object user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
