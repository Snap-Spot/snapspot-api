package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerWithHeartDto {

    private PhotographerResponseDto photographer;
    private Boolean isLiked;

    public PhotographerWithHeartDto(PhotographerResponseDto photographer, Boolean isLiked){
        this.photographer = photographer;
        this.isLiked = isLiked;
    }
}
