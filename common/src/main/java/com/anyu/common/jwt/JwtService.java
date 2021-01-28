package com.anyu.common.jwt;

import com.anyu.common.exception.GlobalException;
import com.anyu.common.result.ResultType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Autowired
    private JwtProperties jwtProperties;

    public  Optional<String> createJwt(String userId, String username, String role) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
            SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder()
                    // 可以将基本不重要的对象信息放到claims
                    .setHeaderParam("typ", "JWT")
                    .setHeaderParam("alg", "HS256")
                    .claim("role", role)
                    .claim("userId", userId)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(jwtProperties.getIss())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(jwtProperties.getAud())          // 代表这个JWT的接收对象；
                    .signWith(secretKey);
            //添加Token过期时间
            Duration expiredHours = jwtProperties.getExpiredHours();
            if (expiredHours != null && expiredHours.toMillis() > 0) {
                Duration expDuration = expiredHours.plusMillis(nowMillis);
                long expMillis = expDuration.toMillis();
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }
            //生成JWT
            return Optional.ofNullable(builder.compact());
        } catch (Exception e) {
            logger.error("jwt 签名失败 ,message:{}",e.getMessage());
            throw GlobalException.causeBy(ResultType.TOKEN_SIGNATURE_ERROR);
        }
    }

    public Optional<Claims> parseJwt(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.ofNullable(claims);
        } catch (Exception e) {
            logger.error("jwt 解析失败 ,message:{}",e.getMessage());
            throw GlobalException.causeBy(ResultType.TOKEN_PARSE_ERROR);
        }
    }



    /**
     * 判断token是否可用
     * @param token token
     */
    public boolean isAvailable(String token) {
        Claims claims = parseJwt(token).orElseThrow(()->GlobalException.causeBy(ResultType.TOKEN_PARSE_ERROR));
        //是否过期
        boolean expired = claims.getExpiration().before(new Date());
        return !expired;
    }
    public Optional getUsername(String token) {
        return Optional.empty();
    }
}
