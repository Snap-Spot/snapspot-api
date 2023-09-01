package snap.api.auth.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.enums.Provider;
import snap.enums.Role;

/**
 * Role: `ROLE_MEMBER` 혹은 `ROLE_PHOTOGRAPHER`로 고정
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    private Role role;
    private String email;
    private String nickname;
    private String password;

    public Member toEntity(String encryptPassword) {
        return Member.builder()
                .nickname(this.nickname)
                .password(encryptPassword)
                .provider(Provider.PROD_SNAPSPOT)
                .email(this.email)
                .role(this.role).build();
    }
}
