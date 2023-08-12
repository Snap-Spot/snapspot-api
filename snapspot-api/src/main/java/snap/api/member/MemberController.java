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

}
