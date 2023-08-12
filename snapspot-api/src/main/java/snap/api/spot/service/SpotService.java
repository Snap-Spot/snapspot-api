package snap.api.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.spot.dto.SpotResponseDto;
import snap.domains.spot.entity.Spot;
import snap.domains.spot.service.SpotDomainService;
import snap.domains.spot.service.SpotImageDomainService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotDomainService spotDomainService;
    private final SpotImageDomainService spotImageDomainService;

    public SpotResponseDto findSpot(Long spotId){
        Spot spot = spotDomainService.findSpot(spotId);
        return new SpotResponseDto(spot, spotImageDomainService.findImagesBySpot(spot));
    }

    public List<SpotResponseDto> findSpotByTheme(String theme){
        List<Spot> spotList = spotDomainService.findSpotListByTheme(theme);

        List<SpotResponseDto> responseDtoList = new ArrayList<>();
        for(Spot spot : spotList){
            responseDtoList.add(
                    new SpotResponseDto(spot, spotImageDomainService.findImagesBySpot(spot))
            );
        }

        return responseDtoList;
    }

    public List<SpotResponseDto> findSpotByArea(Long areaId){
        List<Spot> spotList = spotDomainService.findSpotListByArea(areaId);

        List<SpotResponseDto> responseDtoList = new ArrayList<>();
        for(Spot spot : spotList){
            responseDtoList.add(
                    new SpotResponseDto(spot, spotImageDomainService.findImagesBySpot(spot))
            );
        }

        return responseDtoList;
    }
}
