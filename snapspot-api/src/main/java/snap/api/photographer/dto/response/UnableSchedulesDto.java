package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.PhotographerSchedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnableSchedulesDto {
    private List<LocalDateTime> unableDates;

    public UnableSchedulesDto(List<PhotographerSchedule> schedules){
        this.unableDates = schedules.stream().map(PhotographerSchedule::getUnableDate).collect(Collectors.toList());
    }
}
