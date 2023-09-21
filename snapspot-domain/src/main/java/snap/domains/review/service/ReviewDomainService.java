package snap.domains.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;
import snap.domains.review.entity.Review;
import snap.domains.review.repository.ReviewRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewDomainService {
    private final ReviewRepository reviewRepository;

    public void createReview(Plan plan, String image, Integer score, String title, String comment) {
        reviewRepository.save(
                Review.builder().image(image).title(title).comment(comment).score(score).plan(plan)
                .build());
    }

    public List<Review> findReviewListByPhotographer(Photographer photographer) {
        return reviewRepository.findAllByPlan_Photographer(photographer);
    }

    public List<Review> findReviewListByMember(Member member) {
        return reviewRepository.findAllByPlan_Customer(member);
    }
}
