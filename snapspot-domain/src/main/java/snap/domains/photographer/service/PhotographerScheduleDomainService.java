package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.PhotographerSchedule;
import snap.domains.photographer.repository.PhotographerScheduleRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerScheduleDomainService {

    private final PhotographerScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public List<PhotographerSchedule> findScheduleListByPhotographerId(Long photographerId){
        return scheduleRepository.findAllByPhotographer_PhotographerId(photographerId);
    }
}
