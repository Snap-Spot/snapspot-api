package snap.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.auth.dto.request.SignInRequestDto;
import snap.api.auth.dto.request.SignUpRequestDto;
import snap.api.auth.dto.response.SignInResponseDto;
import snap.api.auth.dto.response.SignUpResponseDto;
import snap.api.auth.service.AuthService;

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
}
