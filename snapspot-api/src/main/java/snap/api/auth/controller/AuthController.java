package snap.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.auth.dto.request.*;
import snap.api.auth.dto.response.SignInResponseDto;
import snap.api.auth.dto.response.SignUpResponseDto;
import snap.api.auth.service.AuthService;
import snap.dto.TokenRes;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        return new ResponseEntity<>(authService.createMember(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto requestDto) {
        return new ResponseEntity<>(authService.createJwt(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/kakao/signup")
    public ResponseEntity<SignUpResponseDto> kakaoSignUp(@RequestBody KaKaoSignUpRequestDto requestDto) {
        return new ResponseEntity<>(authService.createKakaoMember(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/kakao/signin")
    public ResponseEntity<SignInResponseDto> kakoSignIn(@RequestBody KakaoSignInRequestDto requestDto) {
        return ResponseEntity.ok(authService.createJwtOfKakaoMember(requestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenRes> jwtReissue(@RequestBody ReissueRequestDto requestDto) {
        return ResponseEntity.ok(authService.reissue(requestDto.getToken()));
    }
}
