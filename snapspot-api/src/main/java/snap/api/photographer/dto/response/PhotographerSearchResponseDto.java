package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.Photographer;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerSearchResponseDto {
    private List<PhotographerSimpleDto> nicknameResult;
    private List<PhotographerSimpleDto> areaResult;
    private List<PhotographerSimpleDto> recommend;

    public PhotographerSearchResponseDto(
            List<PhotographerSimpleDto> nicknameResult,
            List<PhotographerSimpleDto> areaResult) {
        this.nicknameResult = nicknameResult;
        this.areaResult = areaResult;
    }

    public PhotographerSearchResponseDto(List<PhotographerSimpleDto> random) {
        this.recommend = random;
    }
}
