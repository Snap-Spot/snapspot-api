package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class PhotographerSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Column(name = "unable_date")
    private LocalDateTime unableDate;

    @Builder
    public PhotographerSchedule(Photographer photographer, LocalDateTime unableDate){
        this.photographer = photographer;
        this.unableDate = unableDate;
    }
}
