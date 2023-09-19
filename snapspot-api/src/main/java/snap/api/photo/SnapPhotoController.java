package snap.api.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.photo.dto.SnapPhotoRequestDto;
import snap.api.photo.dto.SnapPhotoResponseDto;
import snap.domains.member.entity.Member;
import snap.resolver.AuthMember;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class SnapPhotoController {

    private final SnapPhotoService snapPhotoService;

    @PostMapping
    public ResponseEntity<SnapPhotoResponseDto> photoRegister(@AuthMember Member member, @RequestBody SnapPhotoRequestDto requestDto) {
        return new ResponseEntity<>(snapPhotoService.createPhoto(member, requestDto.getImageUrl()), HttpStatus.CREATED);
    }
}
