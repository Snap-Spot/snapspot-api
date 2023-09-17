package snap.api.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.review.dto.PhotographerReviewResponseDto;
import snap.api.review.dto.ReviewRequestDto;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.service.PlanDomainService;
import snap.domains.review.entity.Review;
import snap.domains.review.service.ReviewDomainService;
import snap.enums.Status;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewDomainService reviewDomainService;
    private final PlanDomainService planDomainService;

    public void createReview(Member member, ReviewRequestDto request) {
        Plan plan = planDomainService.findByPlanIdAndMember(request.getPlanId(), member);
        if (plan.getStatus().equals(Status.DELIVERY)) {
            reviewDomainService.createReview(plan, request.getImage(), request.getScore(), request.getImage());
        } else {
            throw new IllegalArgumentException("사진 전달이 완료되지 않은 촬영입니다.");
        }
    }


    public PhotographerReviewResponseDto findReviewInfoByPhotographer(Photographer photographer) {
        List<Review> reviewList = reviewDomainService.findReviewListByPhotographer(photographer);
        return new PhotographerReviewResponseDto(photographer, reviewList);
    }

}
