package snap.domains.spot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.spot.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
}
