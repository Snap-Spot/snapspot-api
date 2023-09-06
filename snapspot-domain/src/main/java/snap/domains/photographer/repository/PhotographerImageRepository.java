package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerImage;

import java.util.List;

public interface PhotographerImageRepository extends JpaRepository<PhotographerImage, Long> {

    List<PhotographerImage> findAllByPhotographer(Photographer photographer);
}
