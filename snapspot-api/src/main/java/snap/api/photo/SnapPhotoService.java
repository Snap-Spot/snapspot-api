package snap.api.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.photo.dto.SnapPhotoResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.photo.service.SnapPhotoDomainService;

@Service
@RequiredArgsConstructor
public class SnapPhotoService {

    private final SnapPhotoDomainService snapPhotoDomainService;

    public SnapPhotoResponseDto createPhoto(Member member, String imageUrl) {
        return new SnapPhotoResponseDto(snapPhotoDomainService.createPhoto(member, imageUrl));
    }
}
