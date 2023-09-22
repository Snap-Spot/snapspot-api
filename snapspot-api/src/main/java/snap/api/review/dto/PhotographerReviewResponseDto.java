package snap.api.review.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.domains.photographer.entity.Photographer;
import snap.domains.review.entity.Review;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerReviewResponseDto {
    private Integer totalReview;
    private Double averageScore;
    private List<ReviewResponseDto> reviews;

    public PhotographerReviewResponseDto(List<Review> entities) {
        this.totalReview = entities.size();
        this.averageScore = (entities.stream().mapToDouble(Review::getScore).sum() / entities.size());
        this.reviews = entities.stream().map(ReviewResponseDto::new).sorted(Comparator.comparing(ReviewResponseDto::getScore).reversed()).collect(Collectors.toList());
    }
}
