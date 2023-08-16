package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Photographer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photographerId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private Long lowestPay;

    @Column
    private String paymentImage;

    @Column
    private String bio;

    @Builder
    public Photographer(Member member) {
        this.member = member;
    }
}
