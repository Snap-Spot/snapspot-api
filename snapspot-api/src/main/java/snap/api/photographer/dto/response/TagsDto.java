package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.PhotographerTag;
import snap.domains.photographer.entity.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagsDto {
    private List<String> tags;

    public TagsDto(List<PhotographerTag> tags){
        this.tags = tags.stream().map(PhotographerTag::getTag)
                .map(Tag::getTag)
                .collect(Collectors.toList());
    }
}
