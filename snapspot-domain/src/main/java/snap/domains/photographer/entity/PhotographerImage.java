package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.dto.request.ImageReq;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PhotographerImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @OneToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Column()
    private String image1;

    @Column()
    private String image2;

    @Column()
    private String image3;

    @Column()
    private String image4;

    @Column()
    private String image5;

    @Column()
    private String image6;

    @Column()
    private String image7;

    @Column()
    private String image8;

    @Column()
    private String image9;

    @Column()
    private String image10;

    @Builder
    public PhotographerImage(Photographer photographer) {
        this.photographer = photographer;
    }

    public void update(ImageReq image) {
        this.image1 = image.getImage1();
        this.image2 = image.getImage2();
        this.image3 = image.getImage3();
        this.image4 = image.getImage4();
        this.image5 = image.getImage5();
        this.image6 = image.getImage6();
        this.image7 = image.getImage7();
        this.image8 = image.getImage8();
        this.image9 = image.getImage9();
        this.image10 = image.getImage10();
    }
}
