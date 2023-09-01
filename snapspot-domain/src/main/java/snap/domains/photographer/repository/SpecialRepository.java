package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Special;

public interface SpecialRepository extends JpaRepository<Special, Long> {
}
