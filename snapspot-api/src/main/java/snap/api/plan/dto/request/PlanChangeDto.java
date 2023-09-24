package snap.api.plan.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.plan.entity.Plan;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanChangeDto {
    private UUID planId;
    private String reason;
    private LocalDateTime planDate;
    private String time;
    private Long people;
}