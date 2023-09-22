package snap.api.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import snap.api.photo.dto.SnapPhotoRequestDto;
import snap.api.photo.dto.SnapPhotoResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.photo.service.SnapPhotoDomainService;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.PhotographerDomainService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SnapPhotoService {

    private final SnapPhotoDomainService snapPhotoDomainService;
    private final PhotographerDomainService photographerDomainService;

    public SnapPhotoResponseDto createPhoto(Member member, SnapPhotoRequestDto requestDto) {
        Photographer photographer = photographerDomainService.findById(requestDto.getPhotographerId());
        return new SnapPhotoResponseDto(snapPhotoDomainService.createPhoto(member, requestDto.getImageUrl(), requestDto.getPhotoDate(),
                requestDto.getLocation(), photographer, requestDto.getTag1(), requestDto.getTag2(), requestDto.getTag3()));
    }

    public List<SnapPhotoResponseDto> findPhotoListByMember(Member member, Pageable pageable) {
        return snapPhotoDomainService.findPhotoListByMember(member, pageable).stream().map(SnapPhotoResponseDto::new).toList();
    }
}
