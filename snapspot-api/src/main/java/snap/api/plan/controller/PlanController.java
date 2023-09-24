package snap.api.plan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import snap.api.plan.dto.request.*;
import snap.api.message.service.MessageService;
import snap.api.plan.dto.response.PlanFullResponseDto;
import snap.api.plan.dto.response.PlanPhotographerDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.api.plan.service.PlanService;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.service.PlanDomainService;
import snap.resolver.AuthMember;
import snap.resolver.AuthPhotographer;
import snap.response.SuccessResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final MessageService messageService;
    private final PlanDomainService planDomainService;


    @GetMapping("/member")
    private ResponseEntity<List<PlanResponseDto>> planFindByMember(@AuthMember Member member){
        return ResponseEntity.ok(planService.findAllPlanByMember(member));
    }

    @GetMapping("/photographer")
    private ResponseEntity<List<PlanResponseDto>> planFindByPhotographer(@AuthPhotographer Photographer photographer) {
        return ResponseEntity.ok(planService.findAllPlanByPhotographer(photographer));
    }

    @GetMapping("{planId}")
    private ResponseEntity<PlanFullResponseDto> planFindById(@AuthMember Member member, @PathVariable UUID planId) {
        return ResponseEntity.ok(planService.findPlanById(planId, member));
    }

    @GetMapping("/photographer/client")
    private ResponseEntity<PlanPhotographerDto> planFindByPhotographerClient(@AuthPhotographer Photographer photographer) {
        return ResponseEntity.ok(planService.planFindByPhotographerClient(photographer));
    }

    @PostMapping()
    public ResponseEntity<PlanResponseDto> requestPlan(@AuthMember Member member, @RequestBody @Valid PlanRequestDto requestDto) {
        return new ResponseEntity<>(planService.createRequest(member, requestDto), HttpStatus.CREATED);
    }

    @PutMapping("/refuse")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<SuccessResponse> refusePlan(@AuthMember Member member, @RequestBody RefuseRequestDto requestDto) {
        PlanFullResponseDto responseDto = planService.refusePlan(member, requestDto);
        return ResponseEntity
                .ok(SuccessResponse.builder().
                        code("OK").status(200).message("스냅 사진 촬영 예약 거절 및 취소").details("사진 작가가 스냅 사진 촬영 예약을 거절했습니다.")
                        .build());
    }

    @PutMapping("/deposit")
    public ResponseEntity<PlanFullResponseDto> depositPlan(@AuthMember Member member, @RequestBody DepositRequestDto requestDto) {
        return new ResponseEntity<>(planService.createDeposit(member, requestDto), HttpStatus.OK);
    }

    @PutMapping("/reserve")
    public ResponseEntity<SuccessResponse> reservedPlan(@AuthPhotographer Photographer photographer, @RequestBody PlanReservedDto requestDto) {
        planService.reservePlan(requestDto);
        return ResponseEntity
                .ok(SuccessResponse.builder()
                        .code("OK").status(200).message("스냅 사진 예약이 완료되었습니다.")
                        .details("사진 작가가 촬영에 대한 입금을 확인했고, 촬영 일정을 픽스했습니다.")
                .build());
    }

    @PutMapping("/cancel")
    public ResponseEntity<SuccessResponse> cancelPlan(@AuthMember Member member, @RequestBody PlanCancelDto requestDto) {
        planService.cancelPlan(member, requestDto);
        return ResponseEntity
                .ok(SuccessResponse.builder()
                        .code("OK").status(200).message("스냅 사진 촬영 일정을 취소했습니다.")
                        .details("사진 작가 혹은 일반 고객이 스냅 사진 촬영 일정을 취소했습니다.")
                        .build());
    }

    @PutMapping("/delivery")
    public ResponseEntity<SuccessResponse> deliveryPlan(
            @AuthPhotographer Photographer photographer,
            @RequestParam("file") MultipartFile file,
            @RequestParam("json") String request
    ) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule());
        PlanCompleteDto requestDto = objectMapper.readValue(request, new TypeReference<>() {});
        log.info(requestDto.getPlanId().toString());
        planService.completePlan(file, requestDto);
        return ResponseEntity
                .ok(SuccessResponse.builder()
                        .code("OK").status(200).message("스냅 사진 파일을 전달했습니다.")
                        .details("사진 작가가 스냅 사진을 전달했습니다.")
                        .build());
    }

    @PutMapping("/change")
    public ResponseEntity<SuccessResponse> changePlan(@AuthMember Member member, @RequestBody PlanChangeDto requestDto) {
        planService.changePlan(requestDto);
        return ResponseEntity
                .ok(SuccessResponse.builder()
                        .code("OK").status(200).message("스냅 사진 촬영 일정을 변경 요청했습니다.")
                        .details("일반 고객이 스냅 사진 촬영 일정을 변경 요청했습니다.")
                        .build());
    }

}
