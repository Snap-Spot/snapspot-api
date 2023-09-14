package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerTag;

import java.util.List;

public interface PhotographerTagRepository extends JpaRepository<PhotographerTag, Long> {

    List<PhotographerTag> findAllByTag1OrTag2OrTag3(String tag1, String tag2, String tag3);
}
