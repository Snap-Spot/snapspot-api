package snap.api.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import snap.api.plan.dto.request.*;
import snap.api.plan.dto.response.PlanFullResponseDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.message.entity.Message;
import snap.domains.message.entity.Sender;
import snap.domains.message.service.MessageDomainService;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.PhotographerDomainService;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.service.PlanDomainService;
import snap.enums.Role;
import snap.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanService {

    private final PlanDomainService planDomainService;
    private final PhotographerDomainService photographerDomainService;

    private final MessageDomainService messageDomainService;

    public PlanResponseDto createRequest(Member member, PlanRequestDto requestDto) {
        Photographer photographer = photographerDomainService.findById(requestDto.getPhotographerId());
        Plan plan = planDomainService.createRequest(member, photographer, requestDto.toEntity());
        return new PlanResponseDto(plan);
    }

    public PlanFullResponseDto createDeposit(DepositRequestDto requestDto) {
        Plan plan = planDomainService.createDeposit(requestDto.toEntity());
        return new PlanFullResponseDto(plan);
    }

    public PlanFullResponseDto refusePlan(RefuseRequestDto requestDto) {
        Plan plan = planDomainService.findByPlanId(requestDto.getPlanId());
        Plan updatedPlan = planDomainService.updateState(plan, Status.REFUSE);
        Message message = messageDomainService.createMessage(updatedPlan, requestDto.toEntity(), Sender.PHOTOGRAPHER);
        return new PlanFullResponseDto(plan);
    }

    public List<PlanResponseDto> findAllPlanByPhotographer(Photographer photographer) {
        List<Plan> entities =  planDomainService.findByPhotographer(photographer);
        return entities.stream().map(PlanResponseDto::new).collect(Collectors.toList());
    }

    public List<PlanResponseDto> findAllPlanByMember(Member member) {
        List<Plan> entities = planDomainService.findByMember(member);
        return entities.stream().map(PlanResponseDto::new).collect(Collectors.toList());
    }

    public void reservePlan(PlanReservedDto requestDto) {
        Plan plan = planDomainService.findByPlanId(requestDto.getPlanId());
        Plan updatedPlan = planDomainService.updateState(plan, Status.RESERVED);
        Message message = messageDomainService.createMessage(updatedPlan, requestDto.toEntity(),Sender.PHOTOGRAPHER);
    }

    public void cancelPlan(Member member, PlanCancelDto requestDto) {
        Plan plan = planDomainService.findByPlanId(requestDto.getPlanId());
        Plan updatedPlan = planDomainService.updateState(plan, Status.CANCEL);

        Sender sender = Sender.MEMBER;
        if (member.getRole().equals(Role.ROLE_PHOTOGRAPHER)) {
            sender = Sender.PHOTOGRAPHER;
        }

        Message message = messageDomainService.createMessage(updatedPlan, requestDto.toEntity(), sender);
    }
}
