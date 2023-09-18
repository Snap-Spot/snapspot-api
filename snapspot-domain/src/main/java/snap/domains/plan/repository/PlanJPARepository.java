package snap.domains.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.plan.entity.Plan;
import snap.enums.Status;

import java.util.List;
import java.util.UUID;

public interface PlanJPARepository extends JpaRepository<Plan, UUID> {

    List<Plan> findAllByCustomer(Member member);
    List<Plan> findAllByPhotographer(Photographer photographer);


    List<Plan> findAllByPhotographerAndStatus(Photographer photographer, Status status1);

    List<Plan> findAllByPhotographerAndStatusOrStatus(Photographer photographer, Status status1, Status status2);

    List<Plan> findAllByPhotographerAndStatusOrStatusOrStatus(Photographer photographer, Status status1, Status status2, Status status3);
}
