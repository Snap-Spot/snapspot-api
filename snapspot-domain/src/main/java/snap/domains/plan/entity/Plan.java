package snap.domains.plan.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date planDate;

    @Builder
    public Plan(Member customer, Photographer photographer, Status status, Date planDate) {
        this.customer = customer;
        this.photographer = photographer;
        this.status = status;
        this.planDate = planDate;
    }
}
