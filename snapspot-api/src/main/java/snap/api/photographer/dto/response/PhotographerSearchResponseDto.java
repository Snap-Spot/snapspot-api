package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.Photographer;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerSearchResponseDto {
    private List<PhotographerResponseDto> nicknameResult;
    private List<PhotographerResponseDto> areaResult;
    private List<PhotographerResponseDto> recommend;

    public PhotographerSearchResponseDto(
            List<PhotographerSimpleDto> nicknameResult,
            List<PhotographerSimpleDto> areaResult) {
        this.nicknameResult = nicknameResult;
        this.areaResult = areaResult;
    }

    public PhotographerSearchResponseDto(List<PhotographerResponseDto> random) {
        this.recommend = random;
    }
}
