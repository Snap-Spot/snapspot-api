package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.PhotographerSchedule;

public interface PhotographerScheduleRepository extends JpaRepository<PhotographerSchedule, Long> {
}
