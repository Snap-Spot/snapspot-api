package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.PhotographerTag;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagsDto {
    private String tag1;
    private String tag2;
    private String tag3;

    public TagsDto(PhotographerTag entity) {
        this.tag1 = entity.getTag1();
        this.tag2 = entity.getTag2();
        this.tag3 = entity.getTag3();
    }
}
