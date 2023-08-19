package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.spot.entity.Area;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PhotographerArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pa_id")
    private Long photographerAreaId;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Builder
    public PhotographerArea(Photographer photographer, Area area) {
        this.photographer = photographer;
        this.area = area;
    }
}
