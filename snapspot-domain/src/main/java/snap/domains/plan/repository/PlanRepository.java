package snap.domains.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.plan.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
