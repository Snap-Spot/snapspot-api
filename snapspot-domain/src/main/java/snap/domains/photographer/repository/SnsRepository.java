package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Sns;

import java.util.List;

public interface SnsRepository extends JpaRepository<Sns, Long> {

    List<Sns> findAllByPhotographer_PhotographerId(Long photographerId);
}
