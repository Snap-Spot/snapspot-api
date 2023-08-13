package snap.api.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.auth.dto.SignUpRequestDto;
import snap.api.auth.dto.response.SignUpResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.member.entity.Role;
import snap.domains.member.service.MemberDomainService;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.PhotographerDomainService;
import snap.service.JwtSecurityService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberDomainService memberDomainService;
    private final PhotographerDomainService photographerDomainService;
    private final JwtSecurityService jwtSecurityService;

    public SignUpResponseDto createMember(SignUpRequestDto requestDto) {
        Member member = memberDomainService.createMember(
                requestDto.toEntity(jwtSecurityService.encryptPassword(requestDto.getPassword()))
        );
        if (member.getRole() == Role.ROLE_PHOTOGRAPHER) {
            photographerDomainService.createPhotographer(member);
        }
        return new SignUpResponseDto(member);
    }

}
