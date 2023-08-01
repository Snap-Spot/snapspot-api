package snap.domains.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.spot.entity.Spot;
import snap.domains.spot.repository.SpotRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    @Transactional(readOnly = true)
    public Spot findSpot(Long spotId){
        return spotRepository.findById(spotId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 spot입니다."));
    }
}
