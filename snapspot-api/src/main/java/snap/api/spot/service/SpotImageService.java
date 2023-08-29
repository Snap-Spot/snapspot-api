package snap.api.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.spot.dto.SpotImageResponseDto;
import snap.domains.spot.service.SpotImageDomainService;

@Service
@RequiredArgsConstructor
public class SpotImageService {
    private final SpotImageDomainService spotImageDomainService;

    public SpotImageResponseDto findSpotImage(Long imageId){
        return new SpotImageResponseDto(spotImageDomainService.findSpotImage(imageId));
    }
}
