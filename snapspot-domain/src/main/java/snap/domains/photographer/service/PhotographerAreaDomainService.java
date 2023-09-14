package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerArea;
import snap.domains.photographer.repository.PhotographerAreaRepository;
import snap.domains.spot.entity.Area;
import snap.domains.spot.service.AreaDomainService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerAreaDomainService {

    private final PhotographerAreaRepository photographerAreaRepository;
    private final AreaDomainService areaDomainService;

    @Transactional(readOnly = true)
    public List<Photographer> findPhotographerListByArea(String areaName) {
        List<Area> areaList = areaDomainService.findByMetropolitanAndCity(areaName);
        List<PhotographerArea> photographerAreaList =
                areaList.stream()
                        .flatMap(area -> photographerAreaRepository.findAllByArea(area).stream())
                        .collect(Collectors.toList());
        return photographerAreaList.stream().map(PhotographerArea::getPhotographer)
                .collect(Collectors.toList());
    }

    public void updatePhotographerArea(Photographer photographer, List<Long> areaList) {
        List<Long> existedAreaList = photographer.getAreas()
                .stream()
                .map(area -> area.getArea().getAreaId())
                .collect(Collectors.toList());

        List<PhotographerArea> addList = new ArrayList<>();
        for (Long areaId : areaList) {
            if (!existedAreaList.contains(areaId)) {
                addList.add(
                        PhotographerArea.builder()
                                .area(areaDomainService.findArea(areaId))
                                .photographer(photographer)
                                .build()
                );
            }
        }

        List<PhotographerArea> removeList = new ArrayList<>();
        for (PhotographerArea existedArea : photographer.getAreas()) {
            if (!areaList.contains(existedArea.getPhotographerAreaId())) {
                removeList.add(existedArea);
            }
        }

        photographerAreaRepository.saveAll(addList);
        photographerAreaRepository.deleteAll(removeList);
    }
}
