package snap.api.spot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.spot.dto.SpotAreaRequestDto;
import snap.api.spot.dto.SpotResponseDto;
import snap.api.spot.dto.SpotThemeRequestDto;
import snap.domains.spot.entity.Spot;
import snap.domains.spot.service.SpotImageService;
import snap.domains.spot.service.SpotService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spots")
public class SpotController {

    private final SpotService spotService;
    private final SpotImageService spotImageService;

    @GetMapping("/{spotId}")
    public ResponseEntity<SpotResponseDto> getSpot(@PathVariable Long spotId){
        Spot spot = spotService.findSpot(spotId);
        SpotResponseDto responseDto = new SpotResponseDto(spot, spotImageService.findImagesBySpot(spot));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/theme")
    public ResponseEntity<List<SpotResponseDto>> getSpotListByTheme(@RequestBody SpotThemeRequestDto requestDto){
        List<Spot> spotList = spotService.findSpotListByTheme(requestDto.getTheme());

        List<SpotResponseDto> responseDtoList = new ArrayList<>();
        for(Spot spot : spotList){
            responseDtoList.add(
                    new SpotResponseDto(spot, spotImageService.findImagesBySpot(spot))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/area")
    public ResponseEntity<List<SpotResponseDto>> getSpotListByArea(@RequestBody SpotAreaRequestDto requestDto){
        List<Spot> spotList = spotService.findSpotListByArea(requestDto.getAreaId());

        List<SpotResponseDto> responseDtoList = new ArrayList<>();
        for(Spot spot : spotList){
            responseDtoList.add(
                    new SpotResponseDto(spot, spotImageService.findImagesBySpot(spot))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }
}
