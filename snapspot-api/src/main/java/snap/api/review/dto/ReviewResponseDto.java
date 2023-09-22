package snap.api.review.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.domains.review.entity.Review;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewResponseDto {
    private Long reviewId;
    private PlanResponseDto plan;
    private Integer score;
    private String image;
    private String title;
    private String comment;

    public ReviewResponseDto(Review entity) {
        this.reviewId = entity.getReviewId();
        this.plan = new PlanResponseDto(entity.getPlan());
        this.score = entity.getScore();
        this.image = entity.getImage();
        this.title = entity.getTitle();
        this.comment = entity.getComment();
    }
}
