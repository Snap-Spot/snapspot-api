package snap.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import snap.dto.TokenRes;
import snap.enums.Role;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    private static final String AUTHORITIES_KEY = "role";
    private static final String BEARER_TYPE = "Bearer";
    private static final Long ACCESS_TOKEN_EXPIRE_TIME = (long) (1000 * 60 * 60 * 24 * 3);
    private static final Long REFRESH_TOKEN_EXPIRE_TIME = (long) (1000 * 60 * 60 * 24 * 7);

    private final Key key;

    public JwtTokenUtil(@Value("${spring.jwt.secret-key}") String secretKey) {
        String key = secretKey.replaceAll("\\s", "");
        byte[] keyBytes = Decoders.BASE64.decode(key);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenRes generateToken(Authentication authentication) {
        Long current = (new Date()).getTime();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(new Date(current + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256).compact();
        String refreshToken = Jwts.builder()
                .setExpiration((new Date(current + REFRESH_TOKEN_EXPIRE_TIME)))
                .signWith(key, SignatureAlgorithm.HS256).compact();

        return TokenRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenRes generateTokenForKakao(String email, Role role) {
        Long current = (new Date()).getTime();

        String authorities = role.name();

        String accessToken = Jwts.builder()
                .setSubject(email)
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(new Date(current + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256).compact();
        String refreshToken = Jwts.builder()
                .setSubject(email)
                .setExpiration((new Date(current + REFRESH_TOKEN_EXPIRE_TIME)))
                .signWith(key, SignatureAlgorithm.HS256).compact();

        return TokenRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public String getEmail(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Long getExpiration(String accessToken) {
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(accessToken)
                .getBody().getExpiration();
        return (expirationDate.getTime() - (new Date()).getTime());
    }

    /*
    토큰을 인증하는 메서드
    TODO: Exception 메세지 커스텀하여 에러 발생 메세지 전달
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                 IllegalArgumentException e) {
            throw e;
        }
    }

    public TokenRes reissueToken(String email, Role role, String refreshToken) {
        Long current = (new Date()).getTime();
        String authorities = role.name();
        String accessToken = Jwts.builder()
                .setSubject(email)
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(new Date(current + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256).compact();
        return TokenRes.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}
