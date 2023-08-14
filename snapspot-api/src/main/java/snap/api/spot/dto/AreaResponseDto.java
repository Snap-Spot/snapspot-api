package snap.api.spot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.spot.entity.Area;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AreaResponseDto {

    private Long areaId;
    private String metropolitan;
    private String city;

    public AreaResponseDto(Area area){
        this.areaId = area.getAreaId();
        this.metropolitan = area.getMetropolitan();
        this.city = area.getCity();
    }
}
