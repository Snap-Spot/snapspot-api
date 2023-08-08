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
    private Long memberId;

    @Column
    private String email;

    @Column
    private String profileImage;

    @Column
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Builder
    public Member(String email, String profileImage, String nickname, Provider provider) {
        this.email = email;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.provider = provider;
    }
}
