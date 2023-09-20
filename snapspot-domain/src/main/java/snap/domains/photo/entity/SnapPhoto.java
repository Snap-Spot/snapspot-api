package snap.domains.photo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "photo_date")
    private LocalDateTime photoDate;

    @Column
    private String location;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Column(length = 16)
    private String tag1;

    @Column(length = 16)
    private String tag2;

    @Column(length = 16)
    private String tag3;

    @Builder
    public SnapPhoto(Member member, String imageUrl, LocalDateTime photoDate,
                     String location, Photographer photographer, String tag1, String tag2, String tag3) {
        this.member = member;
        this.imageUrl = imageUrl;
        this.photoDate = photoDate;
        this.location = location;
        this.photographer = photographer;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }
}
