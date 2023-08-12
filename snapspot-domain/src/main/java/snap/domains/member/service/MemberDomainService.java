package snap.domains.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.member.entity.Member;
import snap.domains.member.repository.MemberRepository;
import snap.response.ErrorResponse;
import snap.response.ErrorStatus;

import java.util.Optional;

/**
repository에서 db에 접근하는 메서드만 작성
 */
@Service
@RequiredArgsConstructor
public class MemberDomainService {
    private final MemberRepository memberRepository;

    public Member createMember() {
        return memberRepository.save(Member.builder().email("abc@gmail.com").build());
    }

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
