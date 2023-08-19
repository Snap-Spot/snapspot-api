package snap.api.plan.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.plan.entity.Status;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanResponseDto {
    private LocalDateTime planDate;
    private String category;
    private Long people;
    private String wishPlace;
    private String request;
    private Status status;

    public PlanResponseDto(LocalDateTime planDate, String category, Long people, String wishPlace, String request) {
        this.planDate = planDate;
        this.category = category;
        this.people = people;
        this.wishPlace = wishPlace;
        this.request = request;
        this.status = Status.REQUESTED;
    }
}
