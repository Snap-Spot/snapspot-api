package snap.domains.plan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.message.entity.Message;
import snap.domains.message.entity.Sender;
import snap.domains.message.repository.MessageRepository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.entity.Status;
import snap.domains.plan.repository.PlanRepository;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanDomainService {

    private final PlanRepository planRepository;
    private final MessageRepository messageRepository;

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

    public Plan createDeposit(Plan plan) {
        Plan requestedPlan = findByPlanId(plan.getPlanId());

        requestedPlan.detailPlan(
                plan.getPlanId(),
                plan.getStatus(),
                plan.getPrice(),
                plan.getPlaceName(),
                plan.getPlaceAddress(),
                plan.getMessage()
        );

        messageRepository.save(Message.builder()
                        .plan(requestedPlan)
                        .contents(plan.getMessage())
                        .sender(Sender.PHOTOGRAPHER)
                .build());

        return requestedPlan;
    }

    @Transactional(readOnly = true)
    public Plan findByPlanId(UUID planId) {
        return planRepository.findByPlanId(planId);
    }
}
