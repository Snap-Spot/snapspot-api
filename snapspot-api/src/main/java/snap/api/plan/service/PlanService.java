package snap.api.plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.plan.dto.request.DepositRequestDto;
import snap.api.plan.dto.request.PlanRequestDto;
import snap.api.plan.dto.response.PlanFullResponseDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.PhotographerDomainService;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.service.PlanDomainService;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanDomainService planDomainService;
    private final PhotographerDomainService photographerDomainService;

    public PlanResponseDto createRequest(Member member, PlanRequestDto requestDto) {
        Photographer photographer = photographerDomainService.findById(requestDto.getPhotographerId());
        Plan plan = planDomainService.createRequest(member, photographer, requestDto.toEntity());
        return new PlanResponseDto(plan);
    }

    public PlanFullResponseDto createDeposit(DepositRequestDto requestDto) {
        Plan plan = planDomainService.createDeposit(requestDto.toEntity());
        return new PlanFullResponseDto(plan);
    }
}
