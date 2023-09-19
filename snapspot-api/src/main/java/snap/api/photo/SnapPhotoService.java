package snap.api.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.photo.dto.SnapPhotoRequestDto;
import snap.api.photo.dto.SnapPhotoResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.photo.service.SnapPhotoDomainService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SnapPhotoService {

    private final SnapPhotoDomainService snapPhotoDomainService;

    public SnapPhotoResponseDto createPhoto(Member member, SnapPhotoRequestDto requestDto) {
        return new SnapPhotoResponseDto(snapPhotoDomainService.createPhoto(member, requestDto.getImageUrl(), requestDto.getPhotoDate(),
                requestDto.getLocation(), requestDto.getPhotographerName(), requestDto.getTag()));
    }

    public List<SnapPhotoResponseDto> findPhotoListByMember(Member member) {
        return snapPhotoDomainService.findPhotoListByMember(member).stream().map(SnapPhotoResponseDto::new).toList();
    }
}
