package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerTag;
import snap.domains.photographer.entity.Tag;

import java.util.List;

public interface PhotographerTagRepository extends JpaRepository<PhotographerTag, Long> {

    List<PhotographerTag> findAllByTag(Tag tag);

    List<PhotographerTag> findAllByPhotographer(Photographer photographer);
}
