package snap.domains.plan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.SpecialKeyword;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
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

    @Builder
    public Plan(Member customer, Photographer photographer, Status status, LocalDateTime planDate,
                SpecialKeyword category, Long people, Long price, String wishPlace, String placeName,
                String placeAddress, Long x, Long y, String request) {
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
    }
}
