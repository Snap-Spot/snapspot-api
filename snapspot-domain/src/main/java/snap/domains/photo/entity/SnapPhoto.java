package snap.domains.photo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class SnapPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snap_photo_id")
    private Long snapPhotoId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "image_url", length = 255, nullable = false)
    private String imageUrl;

    @Builder
    public SnapPhoto(Member member, String imageUrl) {
        this.member = member;
        this.imageUrl = imageUrl;
    }
}
