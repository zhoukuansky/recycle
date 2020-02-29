package com.recycle.jjwtToken;

import com.recycle.exception.DescribeException;
import com.recycle.exception.ExceptionEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtToken {
    public static String secret = "RecycleProject";

    //创建token
    public static String createToken(Map<String, Object> claims) {
        //System.out.println(System.currentTimeMillis());
        System.out.println(claims);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 10))
                .signWith(SignatureAlgorithm.HS512, secret) //采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
    }

    //解析token
    public static Map<String, Object> verifyToken(String token) throws Exception {
        Map<String, Object> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            //System.out.println(claims);
        } catch (Exception e) {
            claims = null;
            throw new DescribeException(ExceptionEnum.TOKEN_OUTTIME);
        }
        return claims;
    }
}
