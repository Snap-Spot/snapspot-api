package snap.api.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.photographer.service.PhotographerService;
import snap.api.review.dto.PhotographerReviewResponseDto;
import snap.api.review.dto.ReviewRequestDto;
import snap.api.review.dto.ReviewResponseDto;
import snap.api.review.service.ReviewService;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.resolver.AuthMember;
import snap.resolver.AuthPhotographer;
import snap.response.SuccessResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final PhotographerService photographerService;

    @PostMapping
    private ResponseEntity<SuccessResponse> createReview(@AuthMember Member member, @RequestBody ReviewRequestDto request) {
        reviewService.createReview(member, request);
        return new ResponseEntity<>(SuccessResponse.builder().status(201).code("CREATED").message("리뷰가 등록되었습니다.").build(), HttpStatus.CREATED);
    }

    @GetMapping("/photographer/{photographerId}")
    private ResponseEntity<PhotographerReviewResponseDto> findReviewsByPhotographer(@PathVariable Long photographerId) {
        Photographer photographer = photographerService.findPhotographerEntity(photographerId);
        return new ResponseEntity<>(reviewService.findReviewInfoByPhotographer(photographer), HttpStatus.OK);
    }


    @GetMapping("/member")
    private ResponseEntity<List<ReviewResponseDto>> findReviewByMember(@AuthMember Member member) {
        return new ResponseEntity<>(reviewService.findReviewListByMember(member), HttpStatus.OK);
    }

    @GetMapping("/photographer")
    private ResponseEntity<List<ReviewResponseDto>> findReviewByPhotographer(@AuthPhotographer Photographer photographer) {
        return new ResponseEntity<>(reviewService.findReviewListByPhotographer(photographer), HttpStatus.OK);
    }
}
