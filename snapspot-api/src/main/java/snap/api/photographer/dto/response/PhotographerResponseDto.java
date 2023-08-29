package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.api.member.dto.MemberResponseDto;
import snap.api.spot.dto.AreaResponseDto;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerArea;
import snap.domains.photographer.entity.PhotographerTag;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerResponseDto {
    private MemberResponseDto member;
    private Long lowestPay;
    private String paymentImage;
    private String bio;
    private ImageUrlsDto images;
    private List<AreaResponseDto> areas;
    private UnableSchedulesDto unableSchedules;
    private SnsDto sns;
    private SpecialListDto specialList;
    private TagsDto tags;

    @Builder
    public PhotographerResponseDto(Photographer entity) {
        this.member = new MemberResponseDto(entity.getMember());
        this.lowestPay = entity.getLowestPay();
        this.paymentImage = entity.getPaymentImage();
        this.bio = entity.getBio();
        this.images = new ImageUrlsDto(entity.getImages());
        this.areas = entity.getAreas().stream().map(PhotographerArea::getArea)
                .map(AreaResponseDto::new)
                .collect(Collectors.toList());
        this.unableSchedules = new UnableSchedulesDto(entity.getUnableSchedules());
        this.sns = new SnsDto(entity.getSns());
        this.specialList = new SpecialListDto(entity.getSpecialList());
        this.tags = new TagsDto(entity.getTags().stream()
                .map(PhotographerTag::getTag)
                .collect(Collectors.toList()));
    }
}
