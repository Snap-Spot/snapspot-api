package snap.api.plan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.message.dto.request.RefuseRequestDto;
import snap.api.message.service.MessageService;
import snap.api.plan.dto.request.DepositRequestDto;
import snap.api.plan.dto.request.PlanRequestDto;
import snap.api.plan.dto.response.PlanFullResponseDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.api.plan.service.PlanService;
import snap.domains.member.entity.Member;
import snap.resolver.AuthMember;
import snap.response.SuccessResponse;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    private final MessageService messageService;

    @PostMapping("/request")
    public ResponseEntity<PlanResponseDto> requestPlan(@AuthMember Member member, @RequestBody PlanRequestDto requestDto) {
        return new ResponseEntity<>(planService.createRequest(member, requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/refuse")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<SuccessResponse> refusePlan(@RequestBody RefuseRequestDto requestDto) {
        PlanFullResponseDto responseDto = planService.refusePlan(requestDto);
        return ResponseEntity
                .ok(SuccessResponse.builder().
                        code("OK").status(200).message("스냅 사진 촬영 예약 거절 및 취소").details("사진 작가가 스냅 사진 촬영 예약을 거절했습니다.")
                        .build());
    }



    @PutMapping("/deposit")
    public ResponseEntity<PlanFullResponseDto> depositPlan(@RequestBody DepositRequestDto requestDto) {
        return new ResponseEntity<>(planService.createDeposit(requestDto), HttpStatus.OK);
    }
}
