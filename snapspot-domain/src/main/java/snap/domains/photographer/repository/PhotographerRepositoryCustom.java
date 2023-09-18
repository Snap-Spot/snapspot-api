package snap.domains.photographer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import snap.domains.photographer.entity.Photographer;
import snap.dto.request.PhotographerFilterReq;

public interface PhotographerRepositoryCustom {

    Page<Photographer> searchAll(PhotographerFilterReq photographerFilterReq, Pageable pageable);
}
