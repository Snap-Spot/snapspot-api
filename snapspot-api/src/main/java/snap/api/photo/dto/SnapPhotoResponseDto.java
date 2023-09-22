package snap.api.photo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photo.entity.SnapPhoto;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnapPhotoResponseDto {
    private Long snapPhotoId;
    private String imageUrl;
    private LocalDateTime photoDate;
    private String location;
    private Long photographerId;
    private String photographerName;
    private String tag1;
    private String tag2;
    private String tag3;

    public SnapPhotoResponseDto(SnapPhoto snapPhoto) {
        this.snapPhotoId = snapPhoto.getSnapPhotoId();
        this.imageUrl = snapPhoto.getImageUrl();
        this.photoDate = snapPhoto.getPhotoDate();
        this.location = snapPhoto.getLocation();
        this.photographerId = snapPhoto.getPhotographer().getPhotographerId();
        this.photographerName = snapPhoto.getPhotographer().getMember().getNickname();
        this.tag1 = snapPhoto.getTag1();
        this.tag2 = snapPhoto.getTag2();
        this.tag3 = snapPhoto.getTag3();
    }
}
