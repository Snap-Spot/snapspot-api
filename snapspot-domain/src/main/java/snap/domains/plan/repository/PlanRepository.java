package snap.domains.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {

    List<Plan> findByPhotographer(Photographer photographer);
}
