package snap.api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.api.member.dto.LoginRequestDto;
import snap.api.member.dto.SignupRequestDto;
import snap.domains.member.Member;
import snap.domains.member.repository.MemberRepository;
import snap.domains.member.service.MemberDomainService;
import snap.jwt.JwtTokenUtil;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService{
    private final MemberDomainService memberDomainService;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jwt.secret-key}")
    private static String secretKey;

    // 토큰 만료시간 1시간
    private long tokenValidTime = 60 * 60 * 1000L;

    // 회원가입
    public String signup(SignupRequestDto requestDto) {
        // 이메일 중복 체크
        if (existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException(requestDto.getEmail() + "은 이미 가입된 email입니다.");
        }
        memberRepository.save(requestDto.toEntity());
        return "회원가입되었습니다.";
    }

    // 로그인
    public String login(LoginRequestDto requestDto) {
        Member member = findMemberByEmail(requestDto.getEmail());

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getEncodedPassword()))
            throw new IllegalArgumentException("잘못된 비밀번호입니다");

        // 로그인 성공 후 토큰 생성
        String accessToken = JwtTokenUtil.createAccessToken(member.getEmail(), secretKey, tokenValidTime);

        return "accessToken: " + accessToken;
    }

    @Transactional(readOnly = true)
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("email이" + email + "인 회원이 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

}
