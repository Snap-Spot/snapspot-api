package snap.domains.plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.entity.Status;
import snap.domains.plan.repository.PlanRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanDomainService {

    private final PlanRepository planRepository;

    public Plan createRequested(Member member, Photographer photographer, Plan plan) {
        return planRepository.save(Plan.builder()
                .customer(member)
                .photographer(photographer)
                .status(Status.REQUESTED)
                .planDate(plan.getPlanDate())
                .category(plan.getCategory())
                .people(plan.getPeople())
                .wishPlace(plan.getWishPlace())
                .request(plan.getRequest())
                .build());
    }
}
