package com.yuan.bbs.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * jwt工具类
 */
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire}")
    private long expire;
    @Value("${jwt.header}")
    private String header;

    /**
     * 生成jwt token
     * 可以在jwt中存放角色信息，用户可以知道，但是无法伪造
     */
    public String generateToken(int subject) {
        return this.generateToken(String.valueOf(subject));
    }

    /**
     * 生成jwt token
     * 可以在jwt中存放角色信息，用户可以知道，但是无法伪造
     */
    public String generateToken(String subject) {
        // 当前时间
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .compact();
    }

    /**
     * 从请求头中获取token字段并解析返回uid
     */
    public Integer getUidByRequest(HttpServletRequest request) {
        String token = request.getHeader(header);
        Claims claimByToken = this.getClaimByToken(token);
        Assert.notNull(claimByToken, "token异常");
        String subject = claimByToken.getSubject();
        Assert.notNull(subject, "uid不存在");
        return Integer.valueOf(subject);
    }

    /**
     * 从token中获取subject，当token不对劲的时候，返回null
     */
    public String getSubject(String token) {
        Claims claims = this.getClaimByToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * 获取token中的信息
     *
     * @param token token字符串
     * @return Claims对象
     */
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.debug("validate is token error,msg:", e);
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}