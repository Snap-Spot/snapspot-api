package snap.batch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.plan.service.PlanDomainService;

@Service
@RequiredArgsConstructor
public class JobPlanService {

    private final PlanDomainService planDomainService;

    public void updateStateOfToday() {
        planDomainService.updateStateOfToday();
    }

    public void updateStateOfComplete() {
        planDomainService.updateStateOfComplete();
    }
}
