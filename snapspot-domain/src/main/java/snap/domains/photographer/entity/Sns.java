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

    @ManyToOne
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
    public Sns(Photographer photographer, String homepage, String instagram,
               String kakaoChannel, String twitter, String naverBlog) {
        this.photographer = photographer;
        this.homepage = homepage;
        this.instagram = instagram;
        this.kakaoChannel = kakaoChannel;
        this.twitter = twitter;
        this.naverBlog = naverBlog;
    }
}
