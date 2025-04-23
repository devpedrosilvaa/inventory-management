package tech.silva.inventory.modules.auth.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import tech.silva.inventory.modules.shared.exceptions.InvalidTokenException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JwtUtils {
    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789";
    public static final Long EXPIRE_DAYS = 0L;
    public static final Long EXPIRE_HOURS = 2L;
    public static final Long EXPIRE_MINUTES = 30L;
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(JwtUtils.class);


    private JwtUtils() {
    }

    private static SecretKey generateKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date start){
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String email, String role){
        Date issuedAt = new Date();
        Date limit = toExpireDate(issuedAt);

        String token = Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .subject(email)
                .issuedAt(issuedAt)
                .expiration(limit)
                .signWith(generateKey(), Jwts.SIG.HS256)
                .claim("role", role)
                .compact();
        return new JwtToken(token);
    }

    private static Claims getClaimsFromToken(String token){
        try{
            if (token == null || token.isBlank())
                throw new InvalidTokenException("Token is missing or empty");

            return Jwts.parser()
                    .verifyWith(generateKey())
                    .build().parseSignedClaims(removeBearerPrefix(token)).getPayload();
        }catch (JwtException ex){
            throw new InvalidTokenException("Invalid token: " + ex.getMessage());
        }
    }

    public static String getUsernameFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isTokenValid(String token){
        try{
            if (token == null || token.isBlank())
                throw new InvalidTokenException("Token is missing or empty");

            Jwts.parser()
                    .verifyWith(generateKey())
                    .build().parseSignedClaims(removeBearerPrefix(token));
            return true;
        }catch (JwtException ex){
            throw new InvalidTokenException("Invalid token: " + ex.getMessage());
        }
    }

    private static String removeBearerPrefix(String token) {
        if(token.contains(JWT_BEARER))
            return token.substring(JWT_BEARER.length());
        return token;
    }
}
