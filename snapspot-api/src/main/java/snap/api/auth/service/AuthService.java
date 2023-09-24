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
import snap.dto.TokenRes;
import snap.enums.Provider;
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
        if (memberDomainService.isExistedMemberByEmail(kakaoRes.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 계정입니다.");
        }
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
        Member member = memberDomainService.findMemberByEmailAndProvider(kakaoRes.getEmail(), Provider.PROD_KAKAO);
        return new SignInResponseDto(
                kakaoRes.getEmail(),
                jwtSecurityService.createJwtOfKakaoMember(kakaoRes.getEmail(), member.getRole())
        );
    }

    public TokenRes reissue(String token) {
        Member member = memberDomainService.findMemberByEmail(jwtSecurityService.getEmailFromToken(token))
                .orElseThrow(() -> new IllegalArgumentException("refresh token을 파싱하는 도중 문제가 발생하였습니다. 다시 로그인하시는 것을 권장합니다."));
        if (!jwtSecurityService.validateRefreshToken(member, token)) {
            throw new RuntimeException("refresh token의 기한이 만료되었습니다.");
        }
        return jwtSecurityService.reissueJwt(member, token);
    }
}
