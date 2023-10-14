package snap.api.message.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.message.entity.Message;
import snap.domains.message.entity.Sender;
import snap.enums.Role;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResponseDto {
    private Long messageId;
    private Boolean isMine;
    private Sender sender;
    private String custom;
    private String contents;
    private LocalDateTime createdAt;

    public MessageResponseDto(Message message, Member member) {
        Sender sender = Sender.MEMBER;
        if (member.getRole().equals(Role.ROLE_PHOTOGRAPHER)) {
            sender = Sender.PHOTOGRAPHER;
        }

        this.messageId = message.getMessageId();
        this.custom = message.getCustom();
        this.contents = message.getContents();
        this.sender = message.getSender();
        this.isMine = sender.equals(message.getSender());
        this.createdAt = message.getCreatedAt();
    }
}
