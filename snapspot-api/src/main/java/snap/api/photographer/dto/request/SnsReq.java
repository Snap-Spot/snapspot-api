package snap.api.photographer.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsReq {
    private String instagram;
    private String twitter;
    private String kakaoChannel;
    private String naverBlog;
    private String homepage;
}
