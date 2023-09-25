package snap.domains.photographer.repository;

import snap.domains.photographer.entity.Photographer;
import snap.dto.request.PhotographerFilterReq;

import java.util.List;

public interface PhotographerRepositoryCustom {

    List<Photographer> searchAll(PhotographerFilterReq photographerFilterReq);
}
