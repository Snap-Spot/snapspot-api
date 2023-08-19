package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.repository.PhotographerAreaRepository;
import snap.domains.spot.entity.Area;
import snap.domains.spot.service.AreaDomainService;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerAreaDomainService {

    private final PhotographerAreaRepository photographerAreaRepository;

    @Transactional(readOnly = true)
    public Area findArea(Long photographerId){
        return photographerAreaRepository
                .findByPhotographer_PhotographerId(photographerId)
                .getArea();
    }
}
