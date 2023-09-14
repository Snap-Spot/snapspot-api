package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.Special;
import snap.enums.SpecialKeyword;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpecialListDto {
    private List<SpecialKeyword> keywords;

    public SpecialListDto(List<Special> specialList){
        this.keywords = specialList.stream().map(Special::getKeyword).collect(Collectors.toList());
    }
}
