package snap.api.heart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.heart.dto.HeartCancelResponseDto;
import snap.api.heart.dto.HeartSuccessResponseDto;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.dto.response.PhotographerSimpleDto;
import snap.api.review.service.ReviewService;
import snap.domains.heart.service.HeartDomainService;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartDomainService heartDomainService;
    private final ReviewService reviewService;

    public List<PhotographerSimpleDto> heartListByMember(Member member){
        return heartDomainService.findHeartByMember(member).stream()
                .map(photographer -> new PhotographerSimpleDto(photographer,
                        reviewService.findReviewInfoByPhotographer(photographer))).toList();
    }

    public HeartSuccessResponseDto heartCreate(Member member, Long photographerId){
        return new HeartSuccessResponseDto(heartDomainService.createHeart(member, photographerId));
    }

    public HeartCancelResponseDto heartDelete(Member member, Long photographerId){
        Photographer photographer = heartDomainService.deleteHeart(member, photographerId);
        return new HeartCancelResponseDto(member, photographer);
    }
}
