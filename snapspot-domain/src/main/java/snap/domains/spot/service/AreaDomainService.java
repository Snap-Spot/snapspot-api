package snap.domains.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.spot.entity.Area;
import snap.domains.spot.entity.AreaImage;
import snap.domains.spot.repository.AreaImageRepository;
import snap.domains.spot.repository.AreaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AreaDomainService {

    private final AreaRepository areaRepository;
    private final AreaImageRepository areaImageRepository;

    @Transactional(readOnly = true)
    public Area findArea(Long areaId){
        return areaRepository.findById(areaId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 area입니다."));
    }

    @Transactional(readOnly = true)
    public List<Area> findByMetropolitanAndCity(String area){
        List<Area> metroList = areaRepository.findAllByMetropolitanContaining(area);
        List<Area> cityList = areaRepository.findAllByCityContaining(area);
        List<Area> areaList = new ArrayList<>();
        areaList.addAll(metroList);
        areaList.addAll(cityList);
        return areaList;
    }

    @Transactional(readOnly = true)
    public Page<AreaImage> findAreaImagesForFeed(Pageable pageable) {
        return areaImageRepository.findAll(pageable);
    }
}
