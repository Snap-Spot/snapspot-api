package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Sns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sns_id")
    private Long snsId;

    @OneToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    /*
    TODO: 삭제해야 함
     */
    @Column
    private SnsType type;

    /*
    TODO: 삭제해야 함
     */
    @Column
    private String account;

    @Column(length = 255)
    private String homepage;

    @Column(length = 31)
    private String instagram;

    @Column(length = 31)
    private String kakaoChannel;

    @Column(length = 31)
    private String twitter;

    @Column(length = 255)
    private String naverBlog;

    @Builder
    public Sns(Photographer photographer, SnsType type, String account) {
        this.photographer = photographer;
        this.type = type;
        this.account = account;
    }
}
