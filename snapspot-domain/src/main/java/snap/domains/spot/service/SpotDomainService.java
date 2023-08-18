package snap.domains.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.spot.entity.Area;
import snap.domains.spot.entity.Spot;
import snap.domains.spot.repository.SpotRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpotDomainService {

    private final SpotRepository spotRepository;
    private final AreaDomainService areaDomainService;

    @Transactional(readOnly = true)
    public Spot findSpot(Long spotId){
        return spotRepository.findById(spotId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 spot입니다."));
    }

    @Transactional(readOnly = true)
    public List<Spot> findSpotListByTheme(String theme){
        return spotRepository.findAllByTheme(theme);
    }

    @Transactional(readOnly = true)
    public List<Spot> findSpotListByArea(Long areaId){
        Area area = areaDomainService.findArea(areaId);
        return spotRepository.findAllByArea(area);
    }
}
