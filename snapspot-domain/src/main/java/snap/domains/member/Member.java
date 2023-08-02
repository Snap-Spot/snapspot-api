package snap.domains.member;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(nullable = false, length = 64)
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false, length = 16)
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;

    private String provider;

    @Builder
    public Member(String email, String password, String nickname, String profileImage, String provider) {
        this.email = email;
        this.encodedPassword = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.provider = provider;
    }
}
