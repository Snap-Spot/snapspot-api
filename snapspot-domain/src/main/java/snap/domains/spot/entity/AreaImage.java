package snap.domains.spot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class AreaImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @Column
    private String title;

    @Column
    private String location;

    @Column
    private String photographer;

    @Column
    private String url;
}
