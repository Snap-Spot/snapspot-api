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

    public PhotographerWithHeartDto findPhotographer(Long photographerId, Member member) {
        Photographer photographer = photographerDomainService.findById(photographerId);
        return new PhotographerWithHeartDto(new PhotographerResponseDto(photographer),
                heartDomainService.existsHeart(member, photographer));
    }

    public PhotographerSearchResponseDto findBySearch(String word) {
        List<PhotographerSimpleDto> nicknameResult =
                photographerDomainService.findByNickname(word).stream()
                        .map(PhotographerSimpleDto::new)
                        .collect(Collectors.toList());

        List<PhotographerSimpleDto> areaResult =
                photographerAreaDomainService.findPhotographerListByArea(word).stream()
                        .map(PhotographerSimpleDto::new)
                        .collect(Collectors.toList());

        return new PhotographerSearchResponseDto(nicknameResult, areaResult);
    }

    public List<PhotographerSimpleDto> findByTag(String tag){
        List<Photographer> photographerList = photographerTagDomainService.findPhotographerListByTag(tag);
        return photographerList.stream()
                .map(PhotographerSimpleDto::new)
                .collect(Collectors.toList());
    }

    public List<PhotographerNameResponseDto> findAllNames(){
        return photographerDomainService.findAllToList().stream()
                .map(PhotographerNameResponseDto::new).collect(Collectors.toList());
    }

    public List<PhotographerResponseDto> findAllPhotographers(Pageable pageable){
        return photographerDomainService.findAllToPage(pageable)
                .map(PhotographerResponseDto::new)
                .getContent();
    }

    public List<PhotographerSimpleDto> findByFilter(PhotographerFilterReq filterReq, Pageable pageable){
        return photographerDomainService.findAllByFilter(filterReq, pageable)
                .map(PhotographerSimpleDto::new)
                .getContent();
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


        return new PhotographerResponseDto(photographer);
    }

    public Photographer findPhotographerEntity(Long photographerId) {
        return photographerDomainService.findById(photographerId);
    }
}
