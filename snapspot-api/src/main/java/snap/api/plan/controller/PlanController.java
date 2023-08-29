package snap.api.plan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.plan.dto.request.PlanRequestDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.api.plan.service.PlanService;
import snap.domains.member.entity.Member;
import snap.domains.plan.repository.PlanRepository;
import snap.resolver.AuthMember;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping("/request")
    public ResponseEntity<PlanResponseDto> createRequested(@AuthMember Member member, @RequestBody PlanRequestDto planRequestDto) {
        return new ResponseEntity<>(planService.createRequested(member, planRequestDto), HttpStatus.CREATED);
    }
}
