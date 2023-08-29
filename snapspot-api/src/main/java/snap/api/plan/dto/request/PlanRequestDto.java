package snap.api.plan.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.SpecialKeyword;
import snap.domains.plan.entity.Plan;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanRequestDto {
    private Long photographerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime planDate;
    private String category;
    private Long people;
    private String wishPlace;
    private String request;

    public Plan toEntity() {
        return Plan.builder()
                .planDate(planDate)
                .category(SpecialKeyword.valueOf(category))
                .people(people)
                .wishPlace(wishPlace)
                .request(request)
                .build();
    }


}
