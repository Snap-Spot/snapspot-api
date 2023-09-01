package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.PhotographerImage;

public interface PhotographerImageRepository extends JpaRepository<PhotographerImage, Long> {
}
