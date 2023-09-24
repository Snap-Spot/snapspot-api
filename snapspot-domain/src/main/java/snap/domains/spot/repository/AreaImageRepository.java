package snap.domains.spot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.spot.entity.AreaImage;

public interface AreaImageRepository extends JpaRepository<AreaImage, Long> {
    Page<AreaImage> findAll(Pageable pagenable);
}
