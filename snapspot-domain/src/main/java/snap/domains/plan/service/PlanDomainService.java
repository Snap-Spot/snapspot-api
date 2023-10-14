package snap.domains.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.message.entity.Message;
import snap.domains.message.entity.Sender;
import snap.domains.message.repository.MessageRepository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.repository.PlanQueryDslRepository;
import snap.enums.Status;
import snap.domains.plan.repository.PlanJPARepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PlanDomainService {

    private final PlanJPARepository planRepository;
    private final PlanQueryDslRepository planQueryDslRepository;
    private final MessageRepository messageRepository;

    public Plan createRequest(Member member, Photographer photographer, Plan plan) {
        return planRepository.save(Plan.builder()
                .customer(member)
                .photographer(photographer)
                .status(Status.REQUEST)
                .time(plan.getTime())
                .planDate(plan.getPlanDate())
                .category(plan.getCategory())
                .people(plan.getPeople())
                .wishPlace(plan.getWishPlace())
                .request(plan.getRequest())
                .build());
    }

    public Plan createDeposit(Plan plan, String x, String y) {
        Plan requestedPlan = findByPlanId(plan.getPlanId());

        requestedPlan.detailPlan(
                plan.getPlanId(),
                plan.getStatus(),
                plan.getPrice(),
                plan.getPlaceName(),
                plan.getPlaceAddress(),
                plan.getMessage(),
                x,
                y
        );

        messageRepository.save(Message.builder()
                        .plan(requestedPlan)
                        .custom("입금 요청")
                        .contents(plan.getMessage())
                        .sender(Sender.PHOTOGRAPHER)
                .build());

        return requestedPlan;
    }

    @Transactional(readOnly = true)
    public Plan findByPlanId(UUID planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 촬영 일정입니다."));
    }

    public Plan updateState(Plan plan, Status status) {
        plan.setStatus(status);
        return planRepository.save(plan);
    }

    @Transactional(readOnly = true)
    public List<Plan> findByPhotographer(Photographer photographer) {
        return planRepository.findAllByPhotographerOrderByCreatedAtDesc(photographer);
    }

    @Transactional(readOnly = true)
    public List<Plan> findByMember(Member member) {
        return planRepository.findAllByCustomerOrderByCreatedAtDesc(member);
    }

    public Plan findByPlanIdAndMember(UUID planId, Member member) {
        return planRepository.findByPlanIdAndCustomer(planId, member).orElseThrow(() -> new IllegalArgumentException("올바르지 않은 유저이거나, 유효하지 않은 planId입니다."));
    }

    public void updateStateOfToday() {
        planQueryDslRepository.changePlanStatusOfToday();
    }

    public void updateStateOfComplete() {
        planQueryDslRepository.changePlanStatusTomorrow();
    }

    @Transactional(readOnly = true)
    public List<Plan> findByPhotographerAndStatus1(Photographer photographer, Status status, Status status1) {
        return planRepository.findAllByPhotographerAndStatusOrStatusOrderByCreatedAtDesc(photographer, status, status1);
    }

    @Transactional(readOnly = true)
    public List<Plan> findByPhotographerAndStatus(Photographer photographer, Status status, Status status1, Status status2) {
        return planRepository.findAllByPhotographerAndStatusOrStatusOrStatusOrderByCreatedAtDesc(photographer,status,status1,status2);
    }

    public void changePlan(
            Plan plan,
            LocalDateTime modifiedDate,
            String modifiedTime,
            Long modifiedPeople,
            String message
    ) {
        plan.updatePlan(
                modifiedDate,
                modifiedTime,
                modifiedPeople,
                Status.REQUEST
        );
        messageRepository.save(Message.builder()
                .plan(plan)
                .custom("변경 사유")
                .contents(message)
                .sender(Sender.MEMBER)
                .build());
    }
}
