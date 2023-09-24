package snap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import snap.domains.member.entity.Member;
import snap.dto.TokenRes;
import snap.enums.Role;
import snap.jwt.JwtTokenUtil;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class JwtSecurityService {
    private final JwtTokenUtil jwtUtil;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public TokenRes createJwt(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenRes tokenRes = jwtUtil.generateToken(authentication);
        setRefreshToken(email, tokenRes.getRefreshToken());
        return tokenRes;
    }

    public TokenRes createJwtOfKakaoMember(String email, Role role) {
        TokenRes tokenRes = jwtUtil.generateTokenForKakao(email, role);
        setRefreshToken(email, tokenRes.getRefreshToken());
        return tokenRes;
    }

    public String getEmailFromToken(String token) {
        return jwtUtil.getEmail(token);
    }

    public void setRefreshToken(String email, String refreshToken) {
        redisService.setValues(refreshToken, email, Duration.ofDays(31));
    }

    public TokenRes reissueJwt(Member member, String refreshToken) {
        return jwtUtil.reissueToken(member.getEmail(), member.getRole(), refreshToken);
    }

    public Boolean validateRefreshToken(Member member, String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            return false;
        }
        return redisService.getValues(refreshToken).equals(member.getEmail());
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
