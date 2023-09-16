package snap.api.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.plan.service.PlanDomainService;
import snap.domains.review.service.ReviewDomainService;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewDomainService reviewDomainService;
    private final PlanDomainService planDomainService;

    
}
