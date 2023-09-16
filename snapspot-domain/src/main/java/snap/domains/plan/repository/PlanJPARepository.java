package snap.domains.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;

import java.util.List;
import java.util.UUID;

public interface PlanJPARepository extends JpaRepository<Plan, UUID> {

    List<Plan> findAllByCustomer(Member member);
    List<Plan> findAllByPhotographer(Photographer photographer);
}
