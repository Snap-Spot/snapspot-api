package snap.api.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.api.review.dto.PhotographerReviewResponseDto;
import snap.api.review.dto.ReviewRequestDto;
import snap.api.review.dto.ReviewResponseDto;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;
import snap.domains.plan.service.PlanDomainService;
import snap.domains.review.entity.Review;
import snap.domains.review.service.ReviewDomainService;
import snap.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewDomainService reviewDomainService;
    private final PlanDomainService planDomainService;

    public void createReview(Member member, ReviewRequestDto request) {
        Plan plan = planDomainService.findByPlanIdAndMember(request.getPlanId(), member);
        if (plan.getStatus().equals(Status.DELIVERY)) {
            reviewDomainService.createReview(plan, request.getImage(), request.getScore(), request.getComment());
        } else {
            throw new IllegalArgumentException("사진 전달이 완료되지 않은 촬영입니다.");
        }
    }

    @Transactional(readOnly = true)
    public PhotographerReviewResponseDto findReviewInfoByPhotographer(Photographer photographer) {
        List<Review> reviewList = reviewDomainService.findReviewListByPhotographer(photographer);
        return new PhotographerReviewResponseDto(reviewList);
    }
    
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> findReviewListByMember(Member member) {
        List<Review> reviewList = reviewDomainService.findReviewListByMember(member);
        return reviewList.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }
}
