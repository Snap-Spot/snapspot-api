package snap.domains.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.spot.entity.Spot;
import snap.domains.spot.entity.SpotImage;
import snap.domains.spot.repository.SpotImageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<String> findImagesBySpot(Spot spot){
        List<SpotImage> spotImageList = spotImageRepository.findAllBySpot(spot);

        return spotImageList.stream().map(SpotImage::getImage).collect(Collectors.toList());
    }
}
