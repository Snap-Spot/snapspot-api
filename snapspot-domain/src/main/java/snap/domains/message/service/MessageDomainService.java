package snap.domains.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.message.entity.Message;
import snap.domains.message.entity.Sender;
import snap.domains.message.repository.MessageRepository;
import snap.domains.plan.entity.Plan;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageDomainService {

    private final MessageRepository messageRepository;

    public Message createMessage(Plan plan, Message message, Sender sender) {
        return messageRepository.save(Message.builder()
                .plan(plan)
                .contents(message.getContents())
                .sender(sender)
                .build());
    }
}
