package snap.api.spot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.spot.entity.AreaImage;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AreaImageResponseDto {
    private AreaResponseDto area;
    private String title;
    private String location;
    private String photographer;
    private String url;

    public AreaImageResponseDto(AreaImage areaImage) {
        this.area = new AreaResponseDto(areaImage.getArea());
        this.title = areaImage.getTitle();
        this.location = areaImage.getLocation();
        this.photographer = areaImage.getPhotographer();
        this.url = areaImage.getUrl();
    }
}
