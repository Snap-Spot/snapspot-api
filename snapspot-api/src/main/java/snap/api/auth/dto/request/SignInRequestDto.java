package snap.api.auth.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.dto.TokenRes;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequestDto {
    private String email;
    private String password;
}
