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

    @Column(
            name = "1"
    )
    private String image1;

    @Column(
            name = "2"
    )
    private String image2;

    @Column(
            name = "3"
    )
    private String image3;

    @Column(
            name = "4"
    )
    private String image4;

    @Column(
            name = "5"
    )
    private String image5;

    @Column(
            name = "6"
    )
    private String image6;

    @Column(
            name = "7"
    )
    private String image7;

    @Column(
            name = "8"
    )
    private String image8;

    @Column(
            name = "9"
    )
    private String image9;

    @Column(
            name = "10"
    )
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
