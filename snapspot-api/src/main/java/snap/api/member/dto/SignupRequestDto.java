package snap.api.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import snap.domains.member.Member;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequestDto {

    private PasswordEncoder passwordEncoder;

    private String profileImage;
    private String nickname;
    private String email;
    private String password;

    @Builder
    public SignupRequestDto(String profileImage, String nickname, String email, String password) {
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .profileImage(this.profileImage)
                .nickname(nickname)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
