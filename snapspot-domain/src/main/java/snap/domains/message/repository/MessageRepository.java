package snap.domains.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.message.entity.Message;
import snap.domains.plan.entity.Plan;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByPlan_PlanId(UUID planId);

    List<Message> findByPlan(Plan plan);
}
