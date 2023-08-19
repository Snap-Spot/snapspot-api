package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.PhotographerArea;

public interface PhotographerAreaRepository extends JpaRepository<PhotographerArea, Long> {

    PhotographerArea findByPhotographer_PhotographerId(Long photographerId);
}
