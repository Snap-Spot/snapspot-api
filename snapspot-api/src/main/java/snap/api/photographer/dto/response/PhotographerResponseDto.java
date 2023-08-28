package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.api.member.dto.MemberResponseDto;
import snap.api.spot.dto.AreaResponseDto;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.SpecialKeyword;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerResponseDto {
    private MemberResponseDto member;
    private Long lowestPay;
    private String paymentImage;
    private String bio;
    private AreaResponseDto area;
    private List<String> photographerImages;
    private List<String> tag;
    private List<SnsDto> sns;
    private List<SpecialKeyword> special;
    private List<Timestamp> unableSchedule;

    @Builder
    public PhotographerResponseDto(Photographer entity, AreaResponseDto area,
                                   List<String> photographerImages, List<String> tag,
                                   List<SnsDto> sns, List<SpecialKeyword> special,
                                   List<Timestamp> unableSchedule) {
        this.member = new MemberResponseDto(entity.getMember());
        this.lowestPay = entity.getLowestPay();
        this.paymentImage = entity.getPaymentImage();
        this.bio = entity.getBio();
        this.area = area;
        this.photographerImages = photographerImages;
        this.tag = tag;
        this.sns = sns;
        this.special = special;
        this.unableSchedule = unableSchedule;
    }
}
