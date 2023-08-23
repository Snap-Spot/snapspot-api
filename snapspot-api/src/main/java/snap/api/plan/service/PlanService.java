package snap.api.plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.plan.dto.request.PlanRequestDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.service.PlanDomainService;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanDomainService planDomainService;

    public PlanResponseDto createRequested(PlanRequestDto requestDto) {
        Plan plan = planDomainService.createRequested(requestDto.toEntity());
        return new PlanResponseDto(plan);
    }

}
