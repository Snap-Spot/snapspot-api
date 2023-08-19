package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.PhotographerArea;
import snap.domains.spot.entity.Area;

import java.util.List;

public interface PhotographerAreaRepository extends JpaRepository<PhotographerArea, Long> {

    PhotographerArea findByPhotographer_PhotographerId(Long photographerId);

    List<PhotographerArea> findAllByArea(Area area);
}
