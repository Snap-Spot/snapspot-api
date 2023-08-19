package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Special;

import java.util.List;

public interface SpecialRepository extends JpaRepository<Special, Long> {

    List<Special> findAllByPhotographer_PhotographerId(Long photographerId);
}
