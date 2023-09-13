package snap.api.photographer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import snap.api.photographer.dto.request.PhotographerCustomDto;
import snap.api.photographer.dto.response.PhotographerNameResponseDto;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.dto.response.PhotographerSearchResponseDto;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.*;

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

    public PhotographerResponseDto findPhotographer(Long photographerId) {
        return PhotographerResponseDto.builder()
                .entity(photographerDomainService.findById(photographerId))
                .build();
    }

    public PhotographerSearchResponseDto findBySearch(String word) {
        List<PhotographerResponseDto> nicknameResult =
                photographerDomainService.findByNickname(word).stream()
                        .map(PhotographerResponseDto::new)
                        .collect(Collectors.toList());

        List<PhotographerResponseDto> areaResult =
                photographerAreaDomainService.findPhotographerListByArea(word).stream()
                        .map(PhotographerResponseDto::new)
                        .collect(Collectors.toList());

        return new PhotographerSearchResponseDto(nicknameResult, areaResult);
    }

    public List<PhotographerResponseDto> findByTag(String tag){
        List<Photographer> photographerList = photographerTagDomainService.findPhotographerListByTag(tag);
        return photographerList.stream()
                .map(PhotographerResponseDto::new)
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

        /*
        photographerTagDomainService.updateTag(photographer, dto.getTagList());
         */

        return new PhotographerResponseDto(photographer);
    }
}
