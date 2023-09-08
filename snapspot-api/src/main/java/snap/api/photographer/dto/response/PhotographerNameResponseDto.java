package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.Photographer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerNameResponseDto {
    private Long photographerId;
    private String nickname;

    public PhotographerNameResponseDto(Photographer photographer){
        this.photographerId = photographer.getPhotographerId();
        this.nickname = photographer.getMember().getNickname();
    }
}
