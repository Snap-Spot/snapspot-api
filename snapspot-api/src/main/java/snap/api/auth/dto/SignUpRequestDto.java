package snap.api.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * type: `MEMBER` 혹은 `PHOTOGRAPHER`로 고정
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    private String type;
    private String email;
    private String nickname;
    private String password;
}
