package snap.api.plan.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.message.entity.Message;

import java.util.UUID;

/**
 * 예약거절
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefuseRequestDto {
    private UUID planId;
    private String contents;

    public Message toEntity() {
        return Message.builder()
                .contents(contents)
                .build();
    }
}
