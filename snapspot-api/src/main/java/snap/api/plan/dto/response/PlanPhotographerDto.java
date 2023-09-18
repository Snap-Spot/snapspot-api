package snap.api.plan.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.plan.entity.Plan;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanPhotographerDto {
    private List<PlanResponseDto> request;
    private List<PlanResponseDto> reserved;

    public PlanPhotographerDto(List<Plan> request, List<Plan> reserved) {
        this.request = request.stream().map(PlanResponseDto::new).collect(Collectors.toList());
        this.reserved = reserved.stream().map(PlanResponseDto::new).collect(Collectors.toList());
    }
}
