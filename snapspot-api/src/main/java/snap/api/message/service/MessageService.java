package snap.api.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.message.dto.request.RefuseRequestDto;
import snap.domains.message.service.MessageDomainService;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.service.PlanDomainService;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageDomainService messageDomainService;
    private final PlanDomainService planDomainService;

    public String createRefuse(RefuseRequestDto requestDto) {
        Plan plan = planDomainService.findByPlanId(requestDto.getPlanId());
        return messageDomainService.createRefuse(plan, requestDto.toEntity());
    }
}
