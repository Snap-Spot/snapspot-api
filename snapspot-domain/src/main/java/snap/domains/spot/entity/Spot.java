package snap.domains.spot.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    private String name;

    @Builder
    public Spot(Area area, String name) {
        this.area = area;
        this.name = name;
    }
}
