package snap.api.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.photo.dto.SnapPhotoRequestDto;
import snap.api.photo.dto.SnapPhotoResponseDto;
import snap.domains.member.entity.Member;
import snap.resolver.AuthMember;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class SnapPhotoController {

    private final SnapPhotoService snapPhotoService;

    @PostMapping
    public ResponseEntity<SnapPhotoResponseDto> photoRegister(@AuthMember Member member, @RequestBody SnapPhotoRequestDto requestDto) {
        return new ResponseEntity<>(snapPhotoService.createPhoto(member, requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SnapPhotoResponseDto>> photoList(@AuthMember Member member, @PageableDefault(size = 6) Pageable pageable) {
        return new ResponseEntity<>(snapPhotoService.findPhotoListByMember(member, pageable), HttpStatus.OK);
    }
}
