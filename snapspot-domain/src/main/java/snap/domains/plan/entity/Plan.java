package snap.domains.plan.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member customer;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Column
    private LocalDateTime planDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Long people;

    @Column
    private Long price;

    @Column
    private String wishPlace;

    @Column
    private String address;

    @Column
    private Long x;

    @Column
    private Long y;

    @Column
    private String request;

    @Builder
    public Plan(Member customer, Photographer photographer, LocalDateTime planDate, Status status,
                Long people, Long price, String wishPlace, String address, Long x, Long y, String request) {
        this.customer = customer;
        this.photographer = photographer;
        this.planDate = planDate;
        this.status = status;
        this.people = people;
        this.price = price;
        this.wishPlace = wishPlace;
        this.address = address;
        this.x = x;
        this.y = y;
        this.request = request;
    }
}
