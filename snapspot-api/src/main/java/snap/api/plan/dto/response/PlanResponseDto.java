package snap.api.plan.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.api.member.dto.MemberResponseDto;
import snap.api.message.dto.MessageResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.message.entity.Message;
import snap.enums.SpecialKeyword;
import snap.domains.plan.entity.Plan;
import snap.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanResponseDto {
    private UUID planId;
    private MemberResponseDto customer;
    private MemberResponseDto photographer;
    private LocalDateTime planDate;
    private String time;
    private SpecialKeyword category;
    private Long people;
    private String wishPlace;
    private Long price;
    private String request;
    private Status status;

    @Builder
    public PlanResponseDto(Plan plan) {
        this.planId = plan.getPlanId();
        this.customer = new MemberResponseDto(plan.getCustomer());
        this.photographer = new MemberResponseDto(plan.getPhotographer().getMember());
        this.planDate = plan.getPlanDate();
        this.time = plan.getTime();
        this.category = plan.getCategory();
        this.people = plan.getPeople();
        this.price = plan.getPrice();
        this.wishPlace = plan.getWishPlace();
        this.request = plan.getRequest();
        this.status = plan.getStatus();
    }
}
