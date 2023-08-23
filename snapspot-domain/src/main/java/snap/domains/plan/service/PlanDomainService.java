package snap.domains.plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.repository.PlanRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanDomainService {

    private final PlanRepository planRepository;

    public Plan createRequested(Plan plan) {
        return planRepository.save(plan);
    }

}
