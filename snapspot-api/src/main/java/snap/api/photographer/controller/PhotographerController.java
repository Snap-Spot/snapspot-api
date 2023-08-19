package snap.api.photographer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.service.PhotographerService;
import snap.domains.photographer.entity.Photographer;
import snap.resolver.AuthPhotographer;

import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<List<PhotographerResponseDto>> search(@RequestBody String word){
        return new ResponseEntity<>(photographerService.findBySearch(word), HttpStatus.OK);
    }
}
