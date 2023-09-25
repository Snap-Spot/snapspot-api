package snap.api.photographer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import snap.api.photographer.dto.request.PhotographerCustomDto;
import snap.api.photographer.dto.response.PhotographerNameResponseDto;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.dto.response.PhotographerSearchResponseDto;
import snap.api.photographer.dto.response.PhotographerWithHeartDto;
import snap.api.review.dto.PhotographerReviewResponseDto;
import snap.api.review.service.ReviewService;
import snap.domains.heart.service.HeartDomainService;
import snap.domains.member.entity.Member;
import snap.api.photographer.dto.response.PhotographerSimpleDto;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.*;
import snap.dto.request.PhotographerFilterReq;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotographerService {

    private final PhotographerDomainService photographerDomainService;
    private final PhotographerAreaDomainService photographerAreaDomainService;
    private final PhotographerTagDomainService photographerTagDomainService;
    private final SnsDomainService snsDomainService;
    private final SpecialDomainService specialDomainService;
    private final PhotographerScheduleDomainService photographerScheduleDomainService;
    private final PhotographerImageDomainService photographerImageDomainService;
    private final HeartDomainService heartDomainService;
    private final ReviewService reviewService;

    public PhotographerWithHeartDto findPhotographer(Long photographerId, Member member) {
        Photographer photographer = photographerDomainService.findById(photographerId);
        return new PhotographerWithHeartDto(new PhotographerResponseDto(photographer, findReview(photographer)),
                heartDomainService.existsHeart(member, photographer));
    }

    public PhotographerSearchResponseDto findBySearch(String word) {
        List<PhotographerSimpleDto> nicknameResult =
                photographerDomainService.findByNickname(word).stream()
                        .map(photographer -> new PhotographerSimpleDto(photographer, findReview(photographer)))
                        .collect(Collectors.toList());

        List<PhotographerSimpleDto> areaResult =
                photographerAreaDomainService.findPhotographerListByArea(word).stream()
                        .map(photographer -> new PhotographerSimpleDto(photographer, findReview(photographer)))
                        .collect(Collectors.toList());

        if (nicknameResult.isEmpty() && areaResult.isEmpty()) {
            return new PhotographerSearchResponseDto(
                    photographerDomainService.findRandom().stream()
                            .map(photographer -> new PhotographerSimpleDto(photographer, findReview(photographer)))
                            .collect(Collectors.toList())
            );
        }

        return new PhotographerSearchResponseDto(nicknameResult, areaResult);
    }

    public List<PhotographerSimpleDto> findByTag(String tag){
        List<Photographer> photographerList = photographerTagDomainService.findPhotographerListByTag(tag);
        return photographerList.stream()
                .map(photographer -> new PhotographerSimpleDto(photographer, findReview(photographer)))
                .collect(Collectors.toList());
    }

    public List<PhotographerNameResponseDto> findAllNames(){
        return photographerDomainService.findAllToList().stream()
                .map(PhotographerNameResponseDto::new).collect(Collectors.toList());
    }

    public List<PhotographerSimpleDto> findByFilter(PhotographerFilterReq filterReq){
        return photographerDomainService.findAllByFilter(filterReq).stream()
                .map(photographer -> new PhotographerSimpleDto(photographer, findReview(photographer)))
                .toList();
    }

    public PhotographerResponseDto updatePhotographerInfo(Photographer photographer, PhotographerCustomDto dto){
        photographerDomainService.updatePhotographer(photographer, dto.getNickname(), dto.getProfileImage(),
                dto.getPaymentImage(), dto.getLowestPay(), dto.getBio());

        photographerAreaDomainService.updatePhotographerArea(photographer, dto.getAreaId());

        snsDomainService.updateSns(
                photographer,
                dto.getSns().getInstagram(),
                dto.getSns().getTwitter(),
                dto.getSns().getKakaoChannel(),
                dto.getSns().getNaverBlog(),
                dto.getSns().getHomepage());

        specialDomainService.updateSpecial(photographer, dto.getSpecialList());

        photographerScheduleDomainService.updateSchedule(photographer, dto.getUnableDates());

        photographerImageDomainService.updatePhotographerImage(photographer, dto.getImage());


        photographerTagDomainService.updateTag(photographer, dto.getTag());


        return new PhotographerResponseDto(photographer, findReview(photographer));
    }

    public Photographer findPhotographerEntity(Long photographerId) {
        return photographerDomainService.findById(photographerId);
    }
    
    public PhotographerReviewResponseDto findReview(Photographer photographer){
        return reviewService.findReviewInfoByPhotographer(photographer);
    }
}
