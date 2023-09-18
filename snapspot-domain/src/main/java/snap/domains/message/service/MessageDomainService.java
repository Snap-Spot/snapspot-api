package snap.domains.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.message.entity.Message;
import snap.domains.message.entity.Sender;
import snap.domains.message.repository.MessageRepository;
import snap.domains.plan.entity.Plan;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageDomainService {

    private final MessageRepository messageRepository;

    public Message createMessage(Plan plan, String contents, Sender sender) {
        return messageRepository.save(Message.builder()
                .plan(plan)
                .contents(contents)
                .sender(sender)
                .build());
    }

    public List<Message> findByPlanId(UUID planId) {
        return messageRepository.findAllByPlan_PlanId(planId);
    }
}
