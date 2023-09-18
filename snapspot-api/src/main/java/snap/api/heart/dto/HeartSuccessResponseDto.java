package snap.api.heart.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.heart.entity.Heart;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartSuccessResponseDto {
    private Long heartId;
    private String memberName;
    private String photographerName;
    private String message;

    public HeartSuccessResponseDto(Heart heart){
        this.heartId = heart.getHeartId();
        this.memberName = heart.getMember().getNickname();
        this.photographerName = heart.getPhotographer().getMember().getNickname();
        this.message = "좋아요를 눌렀습니다.";
    }
}
