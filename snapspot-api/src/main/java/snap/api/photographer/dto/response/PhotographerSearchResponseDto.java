package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerSearchResponseDto {
    private List<PhotographerSimpleDto> nicknameResult;
    private List<PhotographerSimpleDto> areaResult;

    public PhotographerSearchResponseDto(
            List<PhotographerSimpleDto> nicknameResult,
            List<PhotographerSimpleDto> areaResult) {
        this.nicknameResult = nicknameResult;
        this.areaResult = areaResult;
    }
}
