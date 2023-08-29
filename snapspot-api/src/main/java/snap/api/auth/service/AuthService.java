package snap.api.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.auth.dto.request.KaKaoSignUpRequestDto;
import snap.api.auth.dto.request.KakaoSignInRequestDto;
import snap.api.auth.dto.request.SignInRequestDto;
import snap.api.auth.dto.request.SignUpRequestDto;
import snap.api.auth.dto.response.SignInResponseDto;
import snap.api.auth.dto.response.SignUpResponseDto;
import snap.domains.member.entity.Member;
import snap.dto.KakaoRes;
import snap.enums.Role;
import snap.domains.member.service.MemberDomainService;
import snap.domains.photographer.service.PhotographerDomainService;
import snap.service.JwtSecurityService;
import snap.service.KakaoOAuthService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberDomainService memberDomainService;
    private final PhotographerDomainService photographerDomainService;
    private final JwtSecurityService jwtSecurityService;
    private final KakaoOAuthService kakaoOAuthService;

    public SignUpResponseDto createMember(SignUpRequestDto requestDto) {
        Member member = memberDomainService.createMember(
                requestDto.toEntity(jwtSecurityService.encryptPassword(requestDto.getPassword()))
        );
        if (member.getRole() == Role.ROLE_PHOTOGRAPHER) {
            photographerDomainService.createPhotographer(member);
        }
        return new SignUpResponseDto(member);
    }

    public SignInResponseDto createJwt(SignInRequestDto requestDto) {
        return new SignInResponseDto(
                requestDto.getEmail(),
                jwtSecurityService.createJwt(requestDto.getEmail(), requestDto.getPassword())
        );
    }

    public SignUpResponseDto createKakaoMember(KaKaoSignUpRequestDto requestDto) {
        KakaoRes kakaoRes = kakaoOAuthService.getKakaoInfo(requestDto.getAccessToken());
        Member member = memberDomainService.createKakaoMember(
                kakaoRes.getNickname(),
                kakaoRes.getProfile(),
                kakaoRes.getEmail(),
                requestDto.getRole()
        );
        if (member.getRole() == Role.ROLE_PHOTOGRAPHER) {
            photographerDomainService.createPhotographer(member);
        }
        return new SignUpResponseDto(member);
    }

    public SignInResponseDto createJwtOfKakaoMember(KakaoSignInRequestDto requestDto) {
        KakaoRes kakaoRes = kakaoOAuthService.getKakaoInfo(requestDto.getAccessToken());
        Member member = memberDomainService.findKakaoMemberByEmail(kakaoRes.getEmail());
        return new SignInResponseDto(
                kakaoRes.getEmail(),
                jwtSecurityService.createJwtOfKakaoMember(kakaoRes.getEmail(), member.getRole())
        );
    }
}
