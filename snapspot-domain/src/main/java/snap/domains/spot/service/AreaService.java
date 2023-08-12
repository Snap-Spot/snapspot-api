package snap.domains.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.spot.entity.Area;
import snap.domains.spot.repository.AreaRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    @Transactional(readOnly = true)
    public Area findArea(Long areaId){
        return areaRepository.findById(areaId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 area입니다."));
    }
}
