package snap.api.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.message.dto.MessageRequestDto;
import snap.api.message.dto.MessageResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.message.entity.Message;
import snap.domains.message.entity.Sender;
import snap.domains.message.service.MessageDomainService;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.service.PlanDomainService;
import snap.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final PlanDomainService planDomainService;
    private final MessageDomainService messageDomainService;

    public Message createMessage(Member member, MessageRequestDto request) {
        Sender sender = Sender.MEMBER;
        if (member.getRole().equals(Role.ROLE_PHOTOGRAPHER)) {
            sender = Sender.PHOTOGRAPHER;
        }
        Plan plan = planDomainService.findByPlanId(request.getPlanId());

        if (!plan.getCustomer().getMemberId().equals(member.getMemberId()))
        {
            if (!plan.getPhotographer().getMember().getMemberId().equals(member.getMemberId())) {
                throw new IllegalArgumentException("사진 촬영 일정에 대해 권한이 있는 일반 계정 혹은 사진작가 계정이 아닙니다.");
            }
        }

        return messageDomainService.createMessage(
                plan,
                request.getContents(),
                sender
        );
    }

    public List<MessageResponseDto> getMessages(Member member, UUID planId) {
        List<Message> messageList = messageDomainService.findByPlanId(planId);
        return messageList.stream()
                .map(message -> new MessageResponseDto(message, member))
                .collect(Collectors.toList());
    }
}
