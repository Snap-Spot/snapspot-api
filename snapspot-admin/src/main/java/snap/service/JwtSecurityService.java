package snap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import snap.dto.TokenRes;
import snap.enums.Role;
import snap.jwt.JwtTokenUtil;

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
        return jwtUtil.generateToken(authentication);
    }

    public TokenRes createJwtOfKakaoMember(String email, Role role) {
        return jwtUtil.generateTokenForKakao(email, role);
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
