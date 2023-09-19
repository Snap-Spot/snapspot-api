package snap.domains.photo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photo.entity.SnapPhoto;

public interface SnapPhotoRepository extends JpaRepository<SnapPhoto, Long> {
}
