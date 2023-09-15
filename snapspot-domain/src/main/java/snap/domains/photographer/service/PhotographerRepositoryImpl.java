package snap.domains.photographer.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.repository.PhotographerRepositoryCustom;
import snap.dto.request.PhotographerFilterReq;
import snap.enums.SpecialKeyword;

import java.time.LocalDateTime;
import java.util.List;

import static snap.domains.photographer.entity.QPhotographer.photographer;
import static snap.domains.photographer.entity.QPhotographerArea.photographerArea;
import static snap.domains.spot.entity.QArea.area;
import static snap.domains.photographer.entity.QPhotographerSchedule.photographerSchedule;
import static snap.domains.photographer.entity.QSpecial.special;

@Repository
@RequiredArgsConstructor
public class PhotographerRepositoryImpl implements PhotographerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Photographer> searchAll(PhotographerFilterReq photographerFilterReq, Pageable pageable) {

        List<Photographer> result = queryFactory
                .selectFrom(photographer)
                .where(
                        areaIdCondition(photographerFilterReq.getAreaId()),
                        specialKeywordCondition(photographerFilterReq.getSpecial()),
                        ableDateCondition(photographerFilterReq.getAbleDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(result);
    }

    private BooleanExpression areaIdCondition(Long areaId) {
        if (areaId != null) {
            return photographer.areas.any().area.areaId.eq(areaId);
        }
        return null;
    }

    private BooleanExpression specialKeywordCondition(SpecialKeyword specialKeyword) {
        if (specialKeyword != null) {
            return photographer.specialList.any().keyword.eq(specialKeyword);
        }
        return null;
    }

    private BooleanExpression ableDateCondition(LocalDateTime ableDate) {
        if (ableDate != null) {
            return photographer.unableSchedules.any().unableDate.eq(ableDate).not();
        }
        return null;
    }
}
