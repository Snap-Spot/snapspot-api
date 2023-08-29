package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.Sns;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsDto {

    private String homepage;
    private String instagram;
    private String kakaoChannel;
    private String twitter;
    private String naverBlog;

    public SnsDto(Sns sns){
        this.homepage = sns.getHomepage();
        this.instagram = sns.getInstagram();
        this.kakaoChannel = sns.getKakaoChannel();
        this.twitter = sns.getTwitter();
        this.naverBlog = sns.getNaverBlog();
    }
}

