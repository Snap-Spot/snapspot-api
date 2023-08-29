package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.PhotographerSchedule;

import java.util.List;

public interface PhotographerScheduleRepository extends JpaRepository<PhotographerSchedule, Long> {

    List<PhotographerSchedule> findAllByPhotographer_PhotographerId(Long photographerId);
}
