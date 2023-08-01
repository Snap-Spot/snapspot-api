package snap.api.spot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.spot.dto.SpotResponseDto;
import snap.domains.spot.service.SpotService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spots")
public class SpotController {

    private final SpotService spotService;

    @GetMapping("/{spotId}")
    public SpotResponseDto getSpot(@PathVariable Long spotId){
        return new SpotResponseDto(spotService.findSpot(spotId));
    }
}
