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
import snap.domains.review.entity.Review;
import snap.enums.SpecialKeyword;
import snap.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
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
    private LocalDateTime planDate;

    @Column
    private String time;

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

    @OneToMany(
            mappedBy = "plan",
            fetch = FetchType.LAZY
    )
    private List<Review> reviews;


    @Builder
    public Plan(UUID planId, Member customer, Photographer photographer, Status status,
                LocalDateTime planDate, String time,
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
        this.time = time;
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

    public void changePlan(LocalDateTime planDate, String time , Long people) {
        this.planDate = planDate;
        this.time = time;
        this.people = people;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
