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
    @Column(name = "photographer_id")
    private Long photographerId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "lowest_pay")
    private Long lowestPay;

    @Column(name = "payment_image")
    private String paymentImage;

    @Column
    private String bio;

    @Builder
    public Photographer(Member member) {
        this.member = member;
    }
}
