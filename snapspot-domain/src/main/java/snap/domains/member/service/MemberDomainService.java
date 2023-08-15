package snap.domains.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.member.entity.Provider;
import snap.domains.member.repository.MemberRepository;

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
        return memberRepository.save(member);
    }
}
