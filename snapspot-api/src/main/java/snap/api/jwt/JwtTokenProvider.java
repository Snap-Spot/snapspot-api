package snap.api.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import snap.api.member.MemberService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.jwt.secret-key}")
    private String secretKey;

    // 토큰 만료시간 1시간
    private long tokenValidTime = 60 * 60 * 1000L;

    private final MemberService memberService;

    // 객체 초기화, secretKey를 Base64로 인코딩
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public static String createToken(String username, String key, long tokenValidTime) {
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 토큰 발행시각
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))   // 토큰 만료시각
                .signWith(SignatureAlgorithm.HS256, key)    // HS256 알고리즘과 key를 이용해 암호화
                .compact();
    }

    // AccessToken 생성
    public static String createAccessToken(String username, String key, long tokenValidTime) {
        return createToken(username, key, tokenValidTime);
    }

    // RefreshToken 생성
    public static String createRefreshToken(String username, String key, long tokenValidTime) {
        return createToken(username, key, tokenValidTime);
    }

    // 토큰에서 인증 정보 조회

    // 토큰에서 username 추출
    public static String getUsername(String token, String key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody().get("username", String.class);
    }

    // Authorization Header 통해 인증
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
