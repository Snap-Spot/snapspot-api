package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Sns;

public interface SnsRepository extends JpaRepository<Sns, Long> {
}
