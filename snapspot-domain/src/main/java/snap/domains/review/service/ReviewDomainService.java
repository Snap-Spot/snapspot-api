package snap.domains.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewDomainService {
    private final ReviewRepository reviewRepository;
}
