package snap.api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.member.service.MemberDomainService;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberDomainService memberDomainService;

    public String signUp() {
        return memberDomainService.createMember().getEmail();
    }
}
