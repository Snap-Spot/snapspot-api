package snap.domains.photo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;

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

    @Column(name = "photographer_name",length = 16)
    private String photographerName;

    @Column(length = 31)
    private String tag;

    @Builder
    public SnapPhoto(Member member, String imageUrl, LocalDateTime photoDate,
                     String location, String photographerName, String tag) {
        this.member = member;
        this.imageUrl = imageUrl;
        this.photoDate = photoDate;
        this.location = location;
        this.photographerName = photographerName;
        this.tag = tag;
    }
}
