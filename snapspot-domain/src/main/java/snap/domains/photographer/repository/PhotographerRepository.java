package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Photographer;

public interface PhotographerRepository extends JpaRepository<Photographer, Long> {
}
