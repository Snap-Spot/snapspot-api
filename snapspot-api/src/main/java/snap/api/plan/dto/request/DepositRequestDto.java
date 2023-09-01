package snap.api.plan.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.plan.entity.Plan;
import snap.enums.Status;

import java.util.UUID;

/**
 * 입금요청
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositRequestDto {
    private UUID planId;
    private Long price;
    private String placeName;
    private String placeAddress;
    private String message;

    public Plan toEntity() {
        return Plan.builder()
                .planId(planId)
                .status(Status.DEPOSIT)
                .price(price)
                .placeName(placeName)
                .placeAddress(placeAddress)
                .message(message)
                .build();
    }
}
