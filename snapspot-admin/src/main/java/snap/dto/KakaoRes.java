package snap.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoRes {
    private String nickname;
    private String profile;
    private String email;

    @Builder
    public KakaoRes(String nickname, String profile, String email) {
        this.nickname = nickname;
        this.profile = profile;
        this.email = email;
    }
}
