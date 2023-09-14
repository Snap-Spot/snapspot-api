package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerSchedule;
import snap.domains.photographer.repository.PhotographerScheduleRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerScheduleDomainService {
    private final PhotographerScheduleRepository scheduleRepository;

    public void updateSchedule(Photographer photographer, List<LocalDateTime> unableDates) {
        List<LocalDateTime> existedUnableDates = photographer.getUnableSchedules()
                .stream().map(PhotographerSchedule::getUnableDate).collect(Collectors.toList());

        if (!(new HashSet<>(unableDates).containsAll(existedUnableDates) && new HashSet<>(existedUnableDates).containsAll(unableDates))) {
            List<PhotographerSchedule> addList = new ArrayList<>();
            for (LocalDateTime dateTime : unableDates) {
                if (!existedUnableDates.contains(dateTime)) {
                    addList.add(PhotographerSchedule.builder().photographer(photographer).unableDate(dateTime).build());
                }
            }

            List<PhotographerSchedule> removeList = new ArrayList<>();
            for (PhotographerSchedule dateTime : photographer.getUnableSchedules()) {
                if (!unableDates.contains(dateTime.getUnableDate())) {
                    removeList.add(dateTime);
                }
            }

            scheduleRepository.saveAllAndFlush(addList);
            scheduleRepository.deleteAll(removeList);
        }
    }
}
