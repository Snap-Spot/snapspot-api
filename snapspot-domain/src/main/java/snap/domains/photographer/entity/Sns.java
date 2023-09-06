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

    @Column(length = 255)
    private String homepage;

    @Column(length = 31)
    private String instagram;

    @Column(length = 31, name = "kakao_channel")
    private String kakaoChannel;

    @Column(length = 31)
    private String twitter;

    @Column(length = 255, name = "naver_blog")
    private String naverBlog;

    @Builder
    public Sns(Photographer photographer) {
        this.photographer = photographer;
    }

    public void updateSns(String instagram, String twitter,
                          String kakaoChannel, String naverBlog, String homepage){
        this.instagram = instagram;
        this.twitter = twitter;
        this.kakaoChannel = kakaoChannel;
        this.naverBlog = naverBlog;
        this.homepage = homepage;
    }
}
