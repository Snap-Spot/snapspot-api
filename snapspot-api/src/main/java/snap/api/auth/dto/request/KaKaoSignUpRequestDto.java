package snap.api.auth.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.enums.Role;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KaKaoSignUpRequestDto {
    private String accessToken;
    private String refreshToken;
    private Role role;
}
