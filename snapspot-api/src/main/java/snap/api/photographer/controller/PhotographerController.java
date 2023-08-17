package snap.api.photographer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.service.PhotographerService;
import snap.domains.photographer.entity.Photographer;
import snap.resolver.AuthPhotographer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photographers")
public class PhotographerController {

    private final PhotographerService photographerService;

    @GetMapping("/me")
    public ResponseEntity<PhotographerResponseDto> photographerInfoFind(@AuthPhotographer Photographer photographer) {
        return new ResponseEntity<>(photographerService.findPhotographer(photographer.getPhotographerId()), HttpStatus.OK);
    }

    @GetMapping("/{photographerId}")
    public ResponseEntity<PhotographerResponseDto> photographerFindById(@PathVariable Long photographerId) {
        return new ResponseEntity<>(photographerService.findPhotographer(photographerId), HttpStatus.OK);
    }
}
