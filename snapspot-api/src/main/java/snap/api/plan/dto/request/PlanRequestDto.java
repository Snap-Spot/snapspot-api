package snap.api.plan.dto.request;

import lombok.Getter;
import snap.domains.plan.entity.Status;

import java.time.LocalDateTime;

@Getter
public class PlanRequestDto {
    private LocalDateTime planDate;
    private String category;
    private Long people;
    private String wishPlace;
    private String request;
    private Status status;

}
