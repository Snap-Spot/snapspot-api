package snap.api.spot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.spot.dto.SpotImageResponseDto;
import snap.domains.spot.service.SpotImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class SpotImageController {

    private final SpotImageService spotImageService;

    @GetMapping("/{imageId}")
    public SpotImageResponseDto getImage(@PathVariable Long imageId){
        return new SpotImageResponseDto(spotImageService.findSpotImage(imageId));
    }
}
