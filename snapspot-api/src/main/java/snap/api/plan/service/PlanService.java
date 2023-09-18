package snap.api.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
import snap.mail.MailDto;
import snap.mail.MailService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanService {

    private final PlanDomainService planDomainService;
    private final PhotographerDomainService photographerDomainService;
    private final MessageDomainService messageDomainService;
    private final MailService mailService;

    public PlanResponseDto createRequest(Member member, PlanRequestDto requestDto) {
        Photographer photographer = photographerDomainService.findById(requestDto.getPhotographerId());
        Plan plan = planDomainService.createRequest(member, photographer, requestDto.toEntity());
        return new PlanResponseDto(plan);
    }

    public PlanFullResponseDto createDeposit(Member member, DepositRequestDto requestDto) {
        Plan plan = planDomainService.createDeposit(requestDto.toEntity());
        return new PlanFullResponseDto(plan, member, messageDomainService.findByPlanId(plan.getPlanId()));
    }

    public PlanFullResponseDto refusePlan(Member member, RefuseRequestDto requestDto) {
        Plan plan = planDomainService.findByPlanId(requestDto.getPlanId());
        Plan updatedPlan = planDomainService.updateState(plan, Status.REFUSE);
        Message message = messageDomainService.createMessage(updatedPlan, requestDto.getContents(), Sender.PHOTOGRAPHER);
        return new PlanFullResponseDto(plan, member, messageDomainService.findByPlanId(plan.getPlanId()));
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
        Message message = messageDomainService.createMessage(updatedPlan,requestDto.getContents(), Sender.PHOTOGRAPHER);
    }

    public void cancelPlan(Member member, PlanCancelDto requestDto) {
        Plan plan = planDomainService.findByPlanId(requestDto.getPlanId());
        Plan updatedPlan = planDomainService.updateState(plan, Status.CANCEL);

        Sender sender = Sender.MEMBER;
        if (member.getRole().equals(Role.ROLE_PHOTOGRAPHER)) {
            sender = Sender.PHOTOGRAPHER;
        }

        Message message = messageDomainService.createMessage(updatedPlan, "취소되었습니다.", sender);
    }

    public void completePlan(MultipartFile file, PlanCompleteDto requestDto) {
        Plan plan = planDomainService.findByPlanId(requestDto.getPlanId());
        Plan updatedPlan = planDomainService.updateState(plan, Status.DELIVERY);
        mailService.mailSendZipFile(MailDto.builder()
                        .planId(updatedPlan.getPlanId())
                        .toEmail(updatedPlan.getCustomer().getEmail())
                        .toName(updatedPlan.getCustomer().getNickname())
                        .message(requestDto.getContents())
                        .file(file)
                .build());
    }

    public PlanFullResponseDto findPlanById(UUID planId, Member member) {
        Plan plan = planDomainService.findByPlanId(planId);
        List<Message> messageList = messageDomainService.findByPlanEntity(plan);
        return new PlanFullResponseDto(plan, member, messageList);
    }
}
