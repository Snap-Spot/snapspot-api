package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.PhotographerImage;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageUrlsDto {
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String image6;
    private String image7;
    private String image8;
    private String image9;
    private String image10;

    public ImageUrlsDto(PhotographerImage images){
        this.image1 = images.getImage1();
        this.image2 = images.getImage2();
        this.image3 = images.getImage3();
        this.image4 = images.getImage4();
        this.image5 = images.getImage5();
        this.image6 = images.getImage6();
        this.image7 = images.getImage7();
        this.image8 = images.getImage8();
        this.image9 = images.getImage9();
        this.image10 = images.getImage10();
    }

}
