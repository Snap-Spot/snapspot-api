package snap.api.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.review.dto.ReviewRequestDto;
import snap.api.review.service.ReviewService;
import snap.domains.member.entity.Member;
import snap.resolver.AuthMember;
import snap.response.SuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    private ResponseEntity<SuccessResponse> createReview (@AuthMember Member member, @RequestBody ReviewRequestDto request) {
        reviewService.createReview(member, request);
        return new ResponseEntity<>(SuccessResponse.builder().status(201).code("CREATED").message("리뷰가 등록되었습니다.").build(), HttpStatus.CREATED);
    }
}
