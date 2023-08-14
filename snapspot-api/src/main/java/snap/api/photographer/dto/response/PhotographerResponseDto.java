package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.api.member.dto.MemberResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerResponseDto {
    private MemberResponseDto member;
    private Long lowestPay;
    private String paymentImage;
    private String bio;

    public PhotographerResponseDto(Photographer entity) {
        this.member = new MemberResponseDto(entity.getMember());
        this.lowestPay = entity.getLowestPay();
        this.paymentImage = entity.getPaymentImage();
        this.bio = entity.getBio();
    }
}
