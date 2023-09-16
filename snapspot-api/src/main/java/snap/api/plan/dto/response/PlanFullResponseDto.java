package snap.api.plan.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.enums.SpecialKeyword;
import snap.domains.plan.entity.Plan;
import snap.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanFullResponseDto {
    private UUID planId;
    private Long customer;
    private Long photographer;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime planDate;
    private SpecialKeyword category;
    private Long people;
    private String wishPlace;
    private String request;
    private Long price;
    private String placeName;
    private String placeAddress;
    private String message;
    private Status status;

    @Builder
    public PlanFullResponseDto(Plan plan) {
        this.planId = plan.getPlanId();
        this.customer = plan.getCustomer().getMemberId();
        this.photographer = plan.getPhotographer().getPhotographerId();
        this.planDate = plan.getPlanDate();
        this.category = plan.getCategory();
        this.people = plan.getPeople();
        this.wishPlace = plan.getWishPlace();
        this.request = plan.getRequest();
        this.price = plan.getPrice();
        this.placeName = plan.getPlaceName();
        this.placeAddress = plan.getPlaceAddress();
        this.message = plan.getMessage();
        this.status = plan.getStatus();
    }
}
