package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.PhotographerTag;

import java.util.List;

public interface PhotographerTagRepository extends JpaRepository<PhotographerTag, Long> {

    List<PhotographerTag> findAllByPhotographer_PhotographerId(Long photographerId);
}
