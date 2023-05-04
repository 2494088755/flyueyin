package com.hy.flyy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author 黄勇
 * @since 2023/3/5
 */
public class JwtUtils {
    //过期时间(1天)
    private static final long expire = 86400;

    //32位密钥
    private static final String secret = "dsadnfsianfisahgndsnionvodsifoia";

    //生成token
    public static String generateToken(String username) {
        Date timeFiled  = new Date();
        Date expiration = new Date(timeFiled.getTime() + 1000 * expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(username)
                .setIssuedAt(timeFiled)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    //解析token
    public static Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
