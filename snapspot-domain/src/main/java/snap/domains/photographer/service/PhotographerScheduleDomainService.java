package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerSchedule;
import snap.domains.photographer.repository.PhotographerScheduleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerScheduleDomainService {
    private final PhotographerScheduleRepository scheduleRepository;

    public void updateSchedule(Photographer photographer, List<LocalDateTime> unableDates) {
        List<PhotographerSchedule> oldList = scheduleRepository.findAllByPhotographer(photographer);
        scheduleRepository.deleteAll(oldList);

        unableDates.forEach(unableDate -> scheduleRepository.save(
                PhotographerSchedule.builder().photographer(photographer).unableDate(unableDate).build()
        ));
    }
}
