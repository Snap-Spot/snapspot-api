package snap.api.plan.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.SpecialKeyword;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.entity.Status;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanResponseDto {
    private LocalDateTime planDate;
    private SpecialKeyword category;
    private Long people;
    private String wishPlace;
    private String request;
    private Status status;

    @Builder
    public PlanResponseDto(Plan plan) {
        planDate = plan.getPlanDate();
        category = plan.getCategory();
        people = plan.getPeople();
        wishPlace = plan.getWishPlace();
        request = plan.getRequest();
        status = plan.getStatus();
    }
}
