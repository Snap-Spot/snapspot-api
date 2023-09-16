package snap.domains.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import snap.enums.Status;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static snap.domains.plan.entity.QPlan.plan;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PlanQueryDslRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public void changePlanStatusOfToday() {
        LocalDateTime now = LocalDateTime.now();
        now = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        queryFactory
                .update(plan)
                .set(plan.status, Status.TODAY)
                .where(plan.planDate.between(now.minusMinutes(10), now.plusHours(23)), plan.status.in(Status.RESERVED))
                .execute();
    }

    public void changePlanStatusTomorrow() {
        LocalDateTime now = LocalDateTime.now();
        now = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
        queryFactory
                .update(plan)
                .set(plan.status, Status.COMPLETE)
                .where(plan.planDate.between(now.plusHours(23), now.plusHours(25)), plan.status.in(Status.TODAY))
                .execute();
    }

}
