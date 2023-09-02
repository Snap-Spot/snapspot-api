package snap.domains.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.plan.entity.Plan;

import java.util.Optional;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {

}
