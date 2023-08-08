package snap.domains.heart.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Builder
    public Heart(Member member, Photographer photographer) {
        this.member = member;
        this.photographer = photographer;
    }
}
