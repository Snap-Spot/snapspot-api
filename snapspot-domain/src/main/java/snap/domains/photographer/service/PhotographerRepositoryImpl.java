package snap.domains.photographer.service;

import com.querydsl.core.types.OrderSpecifier;
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
import snap.enums.Sort;
import snap.enums.SpecialKeyword;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static snap.domains.photographer.entity.QPhotographer.photographer;
import static snap.domains.plan.entity.QPlan.plan;
import static snap.domains.review.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class PhotographerRepositoryImpl implements PhotographerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Photographer> searchAll(PhotographerFilterReq photographerFilterReq, Pageable pageable) {
        List<Photographer> result = new ArrayList<>();
        Sort sort = photographerFilterReq.getSort();

        OrderSpecifier<Long> paySpecifier = photographer.lowestPay.asc();
        OrderSpecifier<Long> reviewCountSpecifier = review.count().desc();
        OrderSpecifier<Long> photographerSpecifier =  photographer.photographerId.asc();
        OrderSpecifier<Double> reviewScoreSpecifier =  review.score.avg().desc();

        if(sort != null) {
            if(sort.equals(Sort.PAY)){
                result = searchQuery(photographerFilterReq, pageable, paySpecifier);
            } else if(sort.equals(Sort.REVIEW)){
                result = searchQuery2(photographerFilterReq, pageable, reviewCountSpecifier, photographerSpecifier);
            } else if (sort.equals(Sort.SCORE)) {
                result = searchQueryDouble(photographerFilterReq, pageable, reviewScoreSpecifier, photographerSpecifier);
            }
        }
        else {
            result = searchQuery(photographerFilterReq, pageable, photographerSpecifier);
        }
        return new PageImpl<>(result);
    }

    private List<Photographer> searchQuery (
            PhotographerFilterReq photographerFilterReq,
            Pageable pageable,
            OrderSpecifier<Long> specifier1
    ) {
        return queryFactory
                .selectFrom(photographer)
                .where(
                        areaIdCondition(photographerFilterReq.getAreaId()),
                        specialKeywordCondition(photographerFilterReq.getSpecial()),
                        ableDateCondition(photographerFilterReq.getAbleDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(specifier1)
                .fetch();
    }

    private List<Photographer> searchQuery2 (
            PhotographerFilterReq photographerFilterReq,
            Pageable pageable,
            OrderSpecifier<Long> specifier1,
            OrderSpecifier<Long> specifier2
    ) {
        return queryFactory
                .selectFrom(photographer)
                .where(
                        areaIdCondition(photographerFilterReq.getAreaId()),
                        specialKeywordCondition(photographerFilterReq.getSpecial()),
                        ableDateCondition(photographerFilterReq.getAbleDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(specifier1, specifier2)
                .fetch();
    }

    private List<Photographer> searchQueryDouble (
            PhotographerFilterReq photographerFilterReq,
            Pageable pageable,
            OrderSpecifier<Double> specifier1,
            OrderSpecifier<Long> specifier2
    ) {
        return queryFactory
                .selectFrom(photographer)
                .where(
                        areaIdCondition(photographerFilterReq.getAreaId()),
                        specialKeywordCondition(photographerFilterReq.getSpecial()),
                        ableDateCondition(photographerFilterReq.getAbleDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(specifier1, specifier2)
                .fetch();
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
