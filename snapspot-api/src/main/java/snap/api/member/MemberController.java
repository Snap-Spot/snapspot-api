package snap.api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.member.dto.LoginRequestDto;
import snap.api.member.dto.MemberModifyRequestDto;
import snap.api.member.dto.MemberResponseDto;
import snap.api.member.dto.SignupRequestDto;
import snap.domains.member.entity.Member;
import snap.resolver.AuthMember;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> memberInfoFind(@AuthMember Member member) {
        return new ResponseEntity<>(new MemberResponseDto(member), HttpStatus.OK);
    }

    @PutMapping("/setting")
    public ResponseEntity<MemberResponseDto> memberUpdate(@AuthMember Member member, @RequestBody MemberModifyRequestDto requestDto){
        return new ResponseEntity<>(memberService.updateMember(member, requestDto), HttpStatus.OK);
    }
}
