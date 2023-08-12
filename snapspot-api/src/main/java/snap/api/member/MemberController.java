package snap.api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.member.dto.LoginRequestDto;
import snap.api.member.dto.SignupRequestDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test success!");
    }

    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String signup(@RequestBody final SignupRequestDto requestDto) {
        return memberService.signup(requestDto);
    }

    // 로그인
    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String login(@RequestBody final LoginRequestDto requestDto) {
        return memberService.login(requestDto);
    }
}
