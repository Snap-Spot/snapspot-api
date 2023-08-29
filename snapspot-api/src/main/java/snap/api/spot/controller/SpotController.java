package snap.api.spot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.spot.dto.SpotAreaRequestDto;
import snap.api.spot.dto.SpotResponseDto;
import snap.api.spot.dto.SpotThemeRequestDto;
import snap.api.spot.service.SpotService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spots")
public class SpotController {

    private final SpotService spotService;

    @GetMapping("/{spotId}")
    public ResponseEntity<SpotResponseDto> getSpot(@PathVariable Long spotId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(spotService.findSpot(spotId));
    }

    @GetMapping("/theme")
    public ResponseEntity<List<SpotResponseDto>> getSpotListByTheme(@RequestBody SpotThemeRequestDto requestDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(spotService.findSpotByTheme(requestDto.getTheme()));
    }

    @GetMapping("/area")
    public ResponseEntity<List<SpotResponseDto>> getSpotListByArea(@RequestBody SpotAreaRequestDto requestDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(spotService.findSpotByArea(requestDto.getAreaId()));
    }
}
