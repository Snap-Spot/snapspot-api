package snap.api.spot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.spot.entity.Spot;
import snap.domains.spot.entity.SpotImage;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotImageResponseDto {

    private Long imageId;
    private Spot spot;
    private String image;

    public SpotImageResponseDto(SpotImage spotImage) {
        this.imageId = spotImage.getImageId();
        this.spot = spotImage.getSpot();
        this.image = spotImage.getImage();
    }
}
