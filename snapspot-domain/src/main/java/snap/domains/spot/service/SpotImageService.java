package snap.domains.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.spot.entity.SpotImage;
import snap.domains.spot.repository.SpotImageRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class SpotImageService {

    private final SpotImageRepository spotImageRepository;

    @Transactional(readOnly = true)
    public SpotImage findSpotImage(Long imageId){
        return spotImageRepository.findById(imageId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 spot image입니다."));
    }
}
