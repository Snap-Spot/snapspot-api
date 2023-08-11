package snap.domains.spot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.spot.entity.Spot;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    List<Spot> findAllByTheme(String theme);
}
