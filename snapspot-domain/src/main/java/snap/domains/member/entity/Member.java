package snap.domains.member.entity;


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

    @Column(nullable = false, length = 64, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false, length = 16)
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;

    @Column
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String email, String password, String nickname, String profileImage, Provider provider, Role role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.provider = provider;
        this.role = role;
    }

    public void updateMember(String nickname, String profileImage){
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
