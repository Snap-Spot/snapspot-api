package snap.domains.plan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.enums.SpecialKeyword;
import snap.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID planId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member customer;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime planDate;

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialKeyword category;

    @Column
    private Long people;

    @Column
    private Long price;

    @Column
    private String wishPlace;

    @Column
    private String placeName;

    @Column
    private String placeAddress;

    @Column
    private Long x;

    @Column
    private Long y;

    @Column
    private String request;

    @Column
    private String message;

    @Builder
    public Plan(UUID planId, Member customer, Photographer photographer, Status status, LocalDateTime planDate,
                SpecialKeyword category, Long people, Long price, String wishPlace, String placeName,
                String placeAddress, Long x, Long y, String request, String message) {
        this.planId = planId;
        this.customer = customer;
        this.photographer = photographer;
        this.status = status;
        this.planDate = planDate;
        this.category = category;
        this.people = people;
        this.price = price;
        this.wishPlace = wishPlace;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.x = x;
        this.y = y;
        this.request = request;
        this.message = message;
    }

    public void detailPlan(UUID planId, Status status, Long price, String placeName, String placeAddress, String message) {
        this.planId = planId;
        this.status = status;
        this.price = price;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.message = message;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
