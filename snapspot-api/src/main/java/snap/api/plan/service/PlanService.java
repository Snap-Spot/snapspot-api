package snap.api.plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.photographer.service.PhotographerService;
import snap.api.plan.dto.request.PlanRequestDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.service.PlanDomainService;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanDomainService planDomainService;
    private final PhotographerService photographerService;

    public PlanResponseDto createRequested(Member member, PlanRequestDto planRequestDto) {
        Long photographerId = planRequestDto.getPhotographerId();
        Photographer photographer = photographerService.findPhotographerEntity(photographerId);
        Plan plan = planDomainService.createRequested(member, photographer, planRequestDto.toEntity());
        return new PlanResponseDto(plan);
    }
}
