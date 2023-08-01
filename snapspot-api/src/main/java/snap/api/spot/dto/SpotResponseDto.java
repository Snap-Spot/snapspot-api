package snap.api.spot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.spot.entity.Area;
import snap.domains.spot.entity.Spot;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotResponseDto {

    private Long spotId;
    private Area area;
    private String name;

    public SpotResponseDto(Spot spot) {
        this.spotId = spot.getSpotId();
        this.area = spot.getArea();
        this.name = spot.getName();
    }
}
