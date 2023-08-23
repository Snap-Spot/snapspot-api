package snap.api.plan.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.SpecialKeyword;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.entity.Status;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanRequestDto {
    private LocalDateTime planDate;
    private SpecialKeyword category;
    private Long people;
    private String wishPlace;
    private String request;

    public Plan toEntity() {
        return Plan.builder()
                .planDate(this.planDate)
                .category(this.category)
                .people(this.people)
                .wishPlace(this.wishPlace)
                .request(this.request)
                .status(Status.REQUESTED)
                .build();
    }
}
