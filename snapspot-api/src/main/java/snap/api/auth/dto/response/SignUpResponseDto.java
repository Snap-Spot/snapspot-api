package snap.api.auth.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpResponseDto {
    private String nickname;
    private String email;
    private String type;

    @Builder
    public SignUpResponseDto(Member entity) {
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.type = "MEMBER";
    }

    @Builder
    public SignUpResponseDto(Photographer entity) {
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.type = "PHOTOGRAPHER";
    }
}
