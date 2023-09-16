package snap.domains.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
