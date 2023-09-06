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

    private String instagram;
    private String twitter;
    private String kakaoChannel;
    private String naverBlog;
    private String homepage;

    private List<SpecialKeyword> specialList;

    private List<String> tagList;

    private List<LocalDateTime> unableDates;

    private List<String> photographerImages;
}
