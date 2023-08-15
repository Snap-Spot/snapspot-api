package snap.api.spot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.spot.entity.Spot;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpotResponseDto {

    private Long spotId;
    private String name;
    private String address;
    private String theme;
    private List<String> images;

    public SpotResponseDto(Spot spot, List<String> images) {
        this.spotId = spot.getSpotId();
        this.name = spot.getName();
        this.address = spot.getAddress();
        this.theme = spot.getTheme();
        this.images = images;
    }
}
