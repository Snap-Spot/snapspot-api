package snap.domains.plan.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.SpecialKeyword;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:MM")
    private LocalDateTime planDate;

    @Column
    private SpecialKeyword category;

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

    public Plan(Member customer, Photographer photographer, Status status, LocalDateTime planDate, SpecialKeyword category,
                Long people, Long price, String wishPlace, String address, Long x, Long y, String request) {
        this.customer = customer;
        this.photographer = photographer;
        this.status = status;
        this.planDate = planDate;
        this.category = category;
        this.people = people;
        this.price = price;
        this.wishPlace = wishPlace;
        this.address = address;
        this.x = x;
        this.y = y;
        this.request = request;
    }
}
