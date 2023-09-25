package snap.domains.photographer.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.repository.PhotographerRepositoryCustom;
import snap.dto.request.PhotographerFilterReq;
import snap.enums.Sort;
import snap.enums.SpecialKeyword;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static snap.domains.photographer.entity.QPhotographer.photographer;
import static snap.domains.review.entity.QReview.review;
import static snap.domains.plan.entity.QPlan.plan;

@Repository
@RequiredArgsConstructor
public class PhotographerRepositoryImpl implements PhotographerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Photographer> searchAll(PhotographerFilterReq photographerFilterReq) {
        List<Photographer> result = new ArrayList<>();
        Sort sort = photographerFilterReq.getSort();

        OrderSpecifier<Long> paySpecifier = photographer.lowestPay.asc();
        OrderSpecifier<Long> reviewCountSpecifier = review.count().desc();
        OrderSpecifier<Long> photographerSpecifier =  photographer.photographerId.asc();
        OrderSpecifier<Double> reviewScoreSpecifier =  review.score.avg().desc();

        if(sort != null) {
            if(sort.equals(Sort.PAY)){
                result = searchQuery(photographerFilterReq).orderBy(paySpecifier, photographerSpecifier).fetch();
            } else if(sort.equals(Sort.REVIEW)){
                result = searchQuery(photographerFilterReq).orderBy(reviewCountSpecifier, photographerSpecifier).fetch();
            } else if (sort.equals(Sort.SCORE)) {
                result = searchQuery(photographerFilterReq).orderBy(reviewScoreSpecifier, photographerSpecifier).fetch();
            }
        }
        else {
            result = searchQuery(photographerFilterReq).orderBy(photographerSpecifier).fetch();
        }
        return result;
    }

    private JPAQuery<Photographer> searchQuery (
            PhotographerFilterReq photographerFilterReq
    ) {
        return queryFactory
                .selectFrom(photographer)
                .leftJoin(photographer.plan, plan)
                .leftJoin(plan.reviews, review)
                .where(
                        areaIdCondition(photographerFilterReq.getAreaId()),
                        specialKeywordCondition(photographerFilterReq.getSpecial()),
                        ableDateCondition(photographerFilterReq.getAbleDate())
                )
                .groupBy(photographer);
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
