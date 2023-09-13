package snap.api.photographer.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.enums.SpecialKeyword;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerCustomDto {
    private String nickname;
    private String profileImage;

    private String paymentImage;
    private Long lowestPay;
    private String bio;

    private List<Long> areaId;

    private Sns sns;

    private List<SpecialKeyword> specialList;

    private List<String> tagList;

    private List<LocalDateTime> unableDates;

    private Image image;
}

@Getter
class Sns {
    private String instagram;
    private String twitter;
    private String kakaoChannel;
    private String naverBlog;
    private String homepage;
}

@Getter
class Image {
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
}
