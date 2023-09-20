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

    @Transactional(readOnly = true)
    public Member findKakaoMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member createKakaoMember(String nickname, String profile, String email, Role role) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 가입된 계정입니다.");
        }
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
}
