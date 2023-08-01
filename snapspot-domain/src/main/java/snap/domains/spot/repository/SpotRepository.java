package snap.domains.spot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.spot.entity.Spot;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}
