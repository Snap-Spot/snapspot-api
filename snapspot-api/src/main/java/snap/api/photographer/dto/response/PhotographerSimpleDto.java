package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.api.spot.dto.AreaResponseDto;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerArea;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerSimpleDto {

    private Long photographerId;
    private String nickname;
    private Long lowestPay;
    private String image;
    private List<AreaResponseDto> areas;
    private SpecialListDto specialList;
    private TagsDto tags;

    /**
     * TODO: review 브랜치 머지 후 별점, 리뷰 수 필드 추가
     *
     */

    @Builder
    public PhotographerSimpleDto(Photographer entity) {
        this.photographerId = entity.getPhotographerId();
        this.nickname = entity.getMember().getNickname();
        this.lowestPay = entity.getLowestPay();
        this.image = entity.getImages().getImage1();
        this.areas = entity.getAreas().stream().map(PhotographerArea::getArea)
                .map(AreaResponseDto::new)
                .collect(Collectors.toList());
        this.specialList = new SpecialListDto(entity.getSpecialList());
        this.tags = new TagsDto(entity.getTags());
    }
}
