package snap.api.plan.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.message.entity.Message;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanCancelDto {
    private UUID planId;
    private String reason;
    private String refundAccount;

    public Message toEntity() {
        return Message.builder()
                .contents(reason)
                .build();
    }
}
