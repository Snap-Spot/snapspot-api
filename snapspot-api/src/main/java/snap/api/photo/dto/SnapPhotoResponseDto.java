package snap.api.photo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photo.entity.SnapPhoto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnapPhotoResponseDto {
    private Long snapPhotoId;
    private String imageUrl;

    public SnapPhotoResponseDto(SnapPhoto snapPhoto) {
        this.snapPhotoId = snapPhoto.getSnapPhotoId();
        this.imageUrl = snapPhoto.getImageUrl();
    }
}
