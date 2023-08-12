package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Provider;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Photographer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photographerId;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column
    private String profileImage;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private Long lowestPay;

    @Column
    private String paymentImage;

    @Builder
    public Photographer(String email, Provider provider, String nickname, String password) {
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
        this.password = password;
    }
}
