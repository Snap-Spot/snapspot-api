package snap.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.auth.dto.SignUpRequestDto;
import snap.api.auth.dto.response.SignUpResponseDto;
import snap.api.auth.service.AuthService;
import snap.api.member.MemberService;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        if (Objects.equals(requestDto.getType(), "PHOTOGRAPHER")) {
            return ResponseEntity.ok().body(authService.createPhotographer(requestDto));
        }
        else if (Objects.equals(requestDto.getType(), "MEMBER")) {
            return ResponseEntity.ok().body(authService.createMember(requestDto));
        }
        else {
            throw new IllegalArgumentException("올바른 계정 타입이 아닙니다. MEMBER 혹은 PHOTOGRAPHER로 입력해주세요.");
        }
    }
}
