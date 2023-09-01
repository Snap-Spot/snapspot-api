package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTag(String tag);

    List<Tag> findAll();
}
