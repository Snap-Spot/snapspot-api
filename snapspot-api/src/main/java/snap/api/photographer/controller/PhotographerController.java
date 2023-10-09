package snap.api.photographer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.member.MemberService;
import snap.api.member.dto.MemberModifyRequestDto;
import snap.api.member.dto.MemberResponseDto;
import snap.api.photographer.dto.request.PhotographerCustomDto;
import snap.api.photographer.dto.response.PhotographerNameResponseDto;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.dto.response.PhotographerSearchResponseDto;
import snap.api.photographer.dto.response.PhotographerWithHeartDto;
import snap.api.photographer.dto.response.PhotographerSimpleDto;
import snap.api.photographer.service.PhotographerService;
import snap.api.review.service.ReviewService;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.dto.request.PhotographerFilterReq;
import snap.resolver.AuthPhotographer;
import snap.service.JwtSecurityService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photographers")
public class PhotographerController {

    private final PhotographerService photographerService;
    private final ReviewService reviewService;
    private final MemberService memberService;
    private final JwtSecurityService jwtSecurityService;

    @GetMapping("/me")
    public ResponseEntity<PhotographerResponseDto> photographerInfoFind(@AuthPhotographer Photographer photographer) {
        return new ResponseEntity<>(new PhotographerResponseDto(photographer, reviewService.findReviewInfoByPhotographer(photographer)), HttpStatus.OK);
    }

    @PutMapping("/me")
    public ResponseEntity<PhotographerResponseDto> photographerInfoUpdate(@AuthPhotographer Photographer photographer,
                                                                          @RequestBody PhotographerCustomDto dto) {
        return new ResponseEntity<>(photographerService.updatePhotographerInfo(photographer, dto), HttpStatus.OK);
    }

    @GetMapping("/{photographerId}")
    public ResponseEntity<PhotographerWithHeartDto> photographerFindById(@PathVariable Long photographerId, HttpServletRequest request) {
        Member member = jwtSecurityService.getMemberByRequest(request).getMember();
        return new ResponseEntity<>(photographerService.findPhotographer(photographerId, member), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<PhotographerSearchResponseDto> search(@RequestParam String keyword){
        return new ResponseEntity<>(photographerService.findBySearch(keyword), HttpStatus.OK);
    }

    @GetMapping("/tag")
    public ResponseEntity<List<PhotographerSimpleDto>> photographerFindByTag(@RequestParam String tag){
        return new ResponseEntity<>(photographerService.findByTag(tag), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PhotographerSimpleDto>> photographerList(PhotographerFilterReq filterReq){
        return new ResponseEntity<>(photographerService.findByFilter(filterReq), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<PhotographerNameResponseDto>> photographerNameList(){
        return new ResponseEntity<>(photographerService.findAllNames(), HttpStatus.OK);
    }

    @PutMapping("/setting")
    public ResponseEntity<MemberResponseDto> photographerUpdate(@AuthPhotographer Photographer photographer, @RequestBody MemberModifyRequestDto requestDto){
        return new ResponseEntity<>(memberService.updateMember(photographer.getMember(), requestDto), HttpStatus.OK);
    }
}
