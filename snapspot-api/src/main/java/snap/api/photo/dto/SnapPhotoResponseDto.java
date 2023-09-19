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
    private String photographerName;
    private String tag;

    public SnapPhotoResponseDto(SnapPhoto snapPhoto) {
        this.snapPhotoId = snapPhoto.getSnapPhotoId();
        this.imageUrl = snapPhoto.getImageUrl();
        this.photoDate = snapPhoto.getPhotoDate();
        this.location = snapPhoto.getLocation();
        this.photographerName = snapPhoto.getPhotographerName();
        this.tag = snapPhoto.getTag();
    }
}
