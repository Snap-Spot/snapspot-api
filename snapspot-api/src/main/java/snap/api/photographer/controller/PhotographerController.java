package snap.api.photographer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.service.PhotographerService;
import snap.api.plan.dto.request.PlanRequestDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.api.plan.service.PlanService;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.repository.PhotographerRepository;
import snap.resolver.AuthMember;
import snap.resolver.AuthPhotographer;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photographers")
public class PhotographerController {

    private final PhotographerService photographerService;
    private final PhotographerRepository photographerRepository;
    private final PlanService planService;

    @GetMapping("/me")
    public ResponseEntity<PhotographerResponseDto> photographerInfoFind(@AuthPhotographer Photographer photographer) {
        return new ResponseEntity<>(photographerService.findPhotographer(photographer.getPhotographerId()), HttpStatus.OK);
    }

    @GetMapping("/{photographerId}")
    public ResponseEntity<PhotographerResponseDto> photographerFindById(@PathVariable Long photographerId) {
        return new ResponseEntity<>(photographerService.findPhotographer(photographerId), HttpStatus.OK);
    }

    @GetMapping("/nickname")
    public ResponseEntity<List<PhotographerResponseDto>> search(@RequestBody String nickname){
        return new ResponseEntity<>(photographerService.searchByNickname(nickname), HttpStatus.OK);
    }

    @PostMapping("/{photographerId}/plan")
    public ResponseEntity<PlanResponseDto> createRequested(@AuthMember Member member, @PathVariable Long photographerId,
                                                           @RequestBody PlanRequestDto requestDto) {
        Photographer photographer = photographerRepository.findByPhotographerId(photographerId);
        return new ResponseEntity<>(planService.createRequested(member, photographer, requestDto), HttpStatus.CREATED);
    }
}
