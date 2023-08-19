package snap.api.photographer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.photographer.dto.request.TagRequestDto;
import snap.api.photographer.service.TagService;
import snap.domains.photographer.entity.Photographer;
import snap.resolver.AuthPhotographer;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<String> tagRegister(@AuthPhotographer Photographer photographer, @RequestBody TagRequestDto requestDto){
        tagService.createTag(photographer, requestDto.getTag());
        return new ResponseEntity<>("태그를 등록하였습니다.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<String>> tagFindStartingWith(@RequestBody TagRequestDto requestDto){
        return new ResponseEntity<>(tagService.findTagStartingWith(requestDto.getTag()), HttpStatus.OK);
    }
}
