package snap.domains.spot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.spot.entity.SpotImage;

public interface SpotImageRepository extends JpaRepository<SpotImage, Long> {
}
