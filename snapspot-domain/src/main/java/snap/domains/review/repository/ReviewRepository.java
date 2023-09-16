package snap.domains.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByPlan_Photographer(Photographer photographer);
    List<Review> findAllByPlan_Customer(Member customer);
}
