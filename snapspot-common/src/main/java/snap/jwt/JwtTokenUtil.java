package snap.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import snap.api.member.MemberService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    // 토큰 생성
    public static String createToken(String email, String key, long tokenValidTime) {
        Claims claims = Jwts.claims();
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 토큰 발행시각
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))   // 토큰 만료시각
                .signWith(SignatureAlgorithm.HS256, key)    // HS256 알고리즘과 key를 이용해 암호화
                .compact();
    }

    // secretKey를 통해 토큰 parsing
    public static Claims extractClaims(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // 토큰에서 email 추출
    public static String getEmail(String token, String secretKey) {
        return extractClaims(token, secretKey).get("email").toString();
    }

    // 토큰 만료 여부 확인
    public static boolean isExpired(String token, String secretKey) {
        Date expiredDate = extractClaims(token, secretKey).getExpiration();
        return expiredDate.before(new Date());
    }

    // AccessToken 생성
    public static String createAccessToken(String email, String secretKey, long tokenValidTime) {
        return createToken(email, secretKey, tokenValidTime);
    }
}
