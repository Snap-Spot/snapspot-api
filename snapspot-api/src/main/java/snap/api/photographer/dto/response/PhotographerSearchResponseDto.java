package snap.api.photographer.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PhotographerSearchResponseDto {
    private List<PhotographerResponseDto> nicknameResult;
    private List<PhotographerResponseDto> areaResult;

    public PhotographerSearchResponseDto(
            List<PhotographerResponseDto> nicknameResult,
            List<PhotographerResponseDto> areaResult) {
        this.nicknameResult = nicknameResult;
        this.areaResult = areaResult;
    }
}
