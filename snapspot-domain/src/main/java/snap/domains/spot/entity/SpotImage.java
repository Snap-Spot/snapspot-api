package snap.domains.spot.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class SpotImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

    private String image;

    @Builder
    public SpotImage(Spot spot, String image) {
        this.spot = spot;
        this.image = image;
    }
}
