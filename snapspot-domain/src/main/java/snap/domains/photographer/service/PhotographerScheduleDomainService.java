package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerSchedule;
import snap.domains.photographer.repository.PhotographerScheduleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerScheduleDomainService {
    private final PhotographerScheduleRepository scheduleRepository;

    public void updateSchedule(Photographer photographer, List<LocalDateTime> unableDates){
        unableDates.stream()
                .map(unableDate -> scheduleRepository.save(
                        PhotographerSchedule.builder()
                                .photographer(photographer)
                                .unableDate(unableDate)
                                .build()
                ))
                .collect(Collectors.toList());
    }
}
