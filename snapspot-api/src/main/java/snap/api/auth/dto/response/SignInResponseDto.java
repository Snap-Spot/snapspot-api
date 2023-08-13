package snap.api.auth.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.member.entity.Role;
import snap.dto.TokenRes;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInResponseDto {
    private String email;
    private TokenRes token;

    public SignInResponseDto(String email, TokenRes token) {
        this.email = email;
        this.token = token;
    }
}
