package snap.domains.spot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.spot.entity.Spot;
import snap.domains.spot.entity.SpotImage;

import java.util.List;

public interface SpotImageRepository extends JpaRepository<SpotImage, Long> {

    List<SpotImage> findAllBySpot(Spot spot);
}
