package snap.domains.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.member.repository.MemberRepository;
import snap.enums.Provider;
import snap.enums.Role;

import java.util.Optional;

/**
repository에서 db에 접근하는 메서드만 작성
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberDomainService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member createMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        return memberRepository.save(member);
    }

    public Member createKakaoMember(String nickname, String profile, String email, Role role) {
        return memberRepository.save(
                Member.builder()
                        .role(role)
                        .provider(Provider.PROD_KAKAO)
                        .nickname(nickname)
                        .email(email)
                        .profileImage(profile)
                        .build()
        );
    }

    public Member updateMember(Member member, String nickname, String profileImage, String email) {
        member.updateMember(nickname, profileImage);
        member.updateEmail(email);
        return member;
    }

    public boolean isExistedMemberByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Member findMemberByEmailAndProvider(String email, Provider provider) {
        return memberRepository.findByEmailAndProvider(email, provider)
                .orElseThrow(() -> new IllegalArgumentException(provider.name() + "에 의해 가입되지 않은 사용자입니다."));
    }
}
