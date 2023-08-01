package snap.domains.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public MemberPhoto(String image, Member member) {
        this.image = image;
        this.member = member;
    }
}
