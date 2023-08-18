package snap.api.spot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.spot.dto.SpotImageResponseDto;
import snap.api.spot.service.SpotImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class SpotImageController {

    private final SpotImageService spotImageService;

    @GetMapping("/{imageId}")
    public ResponseEntity<SpotImageResponseDto> getImage(@PathVariable Long imageId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(spotImageService.findSpotImage(imageId));
    }
}
