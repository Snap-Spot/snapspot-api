package snap.api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.api.member.dto.MemberModifyRequestDto;
import snap.api.member.dto.MemberResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.member.service.MemberDomainService;
import snap.service.PasswordService;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService{
    private final MemberDomainService memberDomainService;
    private final PasswordService passwordService;

    public MemberResponseDto updateMember(Member member, MemberModifyRequestDto requestDto) {
        if (passwordService.verifyPassword(member, requestDto.getPassword())) {
            return new MemberResponseDto(memberDomainService.updateMember(member,
                    requestDto.getNickname(), requestDto.getProfileImage(), requestDto.getEmail()));
        }
        else
            throw new RuntimeException("비밀번호가 맞지 않습니다.");
    }
}
