package snap.api.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberModifyRequestDto {
    private String nickname;
    private String profileImage;
    private String email;
    private String password;
}
