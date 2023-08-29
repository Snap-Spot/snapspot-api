package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.PhotographerArea;

import java.util.Optional;

public interface PhotographerAreaRepository extends JpaRepository<PhotographerArea, Long> {

    Optional<PhotographerArea> findByPhotographer_PhotographerId(Long photographerId);
}
