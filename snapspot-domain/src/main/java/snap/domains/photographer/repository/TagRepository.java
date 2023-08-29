package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByTagStartingWith(String str);

    Boolean existsByTag(String tag);

    Tag findByTag(String tag);
}
