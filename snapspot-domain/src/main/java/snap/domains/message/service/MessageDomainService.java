package snap.domains.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.message.entity.Message;
import snap.domains.message.entity.Sender;
import snap.domains.message.repository.MessageRepository;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.entity.Status;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageDomainService {

    private final MessageRepository messageRepository;

    public String createRefuse(Plan requestedPlan, Message message) {
        messageRepository.save(Message.builder()
                .plan(requestedPlan)
                .contents(message.getContents())
                .sender(Sender.PHOTOGRAPHER)
                .build());

        requestedPlan.statusChange(
                Status.REFUSE
        );

        return "예약 신청이 거절되었습니다.";
    }
}
