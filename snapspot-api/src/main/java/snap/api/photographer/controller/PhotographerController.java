package snap.api.photographer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.domains.photographer.entity.Photographer;
import snap.resolver.AuthPhotographer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photographers")
public class PhotographerController {

    @GetMapping("/me")
    public ResponseEntity<PhotographerResponseDto> memberInfoFind(@AuthPhotographer Photographer photographer) {
        return new ResponseEntity<>(new PhotographerResponseDto(photographer), HttpStatus.OK);
    }
}
