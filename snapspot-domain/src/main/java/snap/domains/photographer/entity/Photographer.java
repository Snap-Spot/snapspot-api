package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.plan.entity.Plan;

import javax.persistence.*;
import java.util.List;

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

    @OneToOne(mappedBy = "photographer")
    private PhotographerImage images;

    @OneToMany(
            mappedBy = "photographer"
    )
    private List<PhotographerArea> areas;

    @OneToMany(
            mappedBy = "photographer"
    )
    private List<PhotographerSchedule> unableSchedules;

    @OneToMany(
            mappedBy = "photographer",
            fetch = FetchType.LAZY
    )
    private List<Plan> plan;

    @OneToOne(mappedBy = "photographer")
    private Sns sns;

    @OneToMany(
            mappedBy = "photographer"
    )
    private List<Special> specialList;

    @OneToOne(
            mappedBy = "photographer"
    )
    private PhotographerTag tags;

    @Builder
    public Photographer(Member member, Long lowestPay, String paymentImage, String bio) {
        this.member = member;
        this.lowestPay = lowestPay;
        this.paymentImage = paymentImage;
        this.bio = bio;
    }

    public void updatePhotographer(
            String nickname, String profileImage, String paymentImage, Long lowestPay, String bio) {
        this.member.updateMember(nickname, profileImage);
        this.paymentImage = paymentImage;
        this.lowestPay = lowestPay;
        this.bio = bio;
    }
}
