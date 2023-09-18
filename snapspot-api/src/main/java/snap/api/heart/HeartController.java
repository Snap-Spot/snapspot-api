package snap.api.heart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.heart.dto.HeartSuccessResponseDto;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.domains.member.entity.Member;
import snap.resolver.AuthMember;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hearts")
public class HeartController {

    private final HeartService heartService;

    @GetMapping
    public ResponseEntity<List<PhotographerResponseDto>> heartList(@AuthMember Member member){
        return new ResponseEntity<>(heartService.heartListByMember(member), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HeartSuccessResponseDto> heartCreate(@AuthMember Member member, @RequestBody Long photographerId){
        return new ResponseEntity<>(heartService.heartCreate(member, photographerId), HttpStatus.OK);
    }
}
