package snap.api.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.dto.response.PhotographerSearchResponseDto;
import snap.api.photographer.dto.response.SnsDto;
import snap.api.spot.dto.AreaResponseDto;
import snap.domains.photographer.entity.*;
import snap.domains.photographer.service.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotographerService {

    private final PhotographerDomainService photographerDomainService;
    private final PhotographerAreaDomainService photographerAreaDomainService;
    private final PhotographerImageDomainService photographerImageDomainService;
    private final PhotographerTagDomainService photographerTagDomainService;
    private final SnsDomainService snsDomainService;
    private final SpecialDomainService specialDomainService;
    private final PhotographerScheduleDomainService photographerScheduleDomainService;


    public PhotographerResponseDto findPhotographer(Long photographerId) {
        return PhotographerResponseDto.builder()
                .entity(photographerDomainService.findById(photographerId))
                .area(findArea(photographerId))
                .photographerImages(findImageList(photographerId))
                .tag(findTagList(photographerId))
                .sns(findSnsList(photographerId))
                .special(findKeywordList(photographerId))
                .unableSchedule(findUnableDateList(photographerId))
                .build();
    }

    public AreaResponseDto findArea(Long photographerId) {
        return new AreaResponseDto(
                photographerAreaDomainService.findAreaByPhotographer(photographerId)
        );
    }

    public List<String> findImageList(Long photographerId) {
        List<PhotographerImage> photographerImageList = photographerImageDomainService.findImageListByPhotographerId(photographerId);
        return photographerImageList.stream().map(PhotographerImage::getImage).collect(Collectors.toList());
    }

    public List<String> findTagList(Long photographerId) {
        List<PhotographerTag> photographerTagList = photographerTagDomainService.findTagList(photographerId);
        return photographerTagList.stream().map(PhotographerTag::getTag).map(Tag::getTag).collect(Collectors.toList());
    }

    public List<SnsDto> findSnsList(Long photographerId) {
        List<Sns> snsList = snsDomainService.findSnsListByPhotographerId(photographerId);
        return snsList.stream().map(SnsDto::new).collect(Collectors.toList());
    }

    public List<SpecialKeyword> findKeywordList(Long photographerId) {
        List<Special> specialList = specialDomainService.findSpecialListByPhotographerId(photographerId);
        return specialList.stream().map(Special::getKeyword).collect(Collectors.toList());
    }

    public List<Timestamp> findUnableDateList(Long photographerId) {
        List<PhotographerSchedule> scheduleList = photographerScheduleDomainService.findScheduleListByPhotographerId(photographerId);
        return scheduleList.stream().map(PhotographerSchedule::getUnableDate).collect(Collectors.toList());
    }

    public PhotographerSearchResponseDto findBySearch(String word) {
        List<PhotographerResponseDto> nicknameResult =
                photographerDomainService.findByNickname(word).stream()
                        .map(Photographer::getPhotographerId)
                        .map(this::findPhotographer)
                        .collect(Collectors.toList());

        List<PhotographerResponseDto> areaResult =
                photographerAreaDomainService.findPhotographerListByArea(word).stream()
                        .map(Photographer::getPhotographerId)
                        .map(this::findPhotographer)
                        .collect(Collectors.toList());

        return new PhotographerSearchResponseDto(nicknameResult, areaResult);
    }

    public List<PhotographerResponseDto> findByTag(String tag){
        List<Photographer> photographerList = photographerTagDomainService.findPhotographerListByTag(tag);
        return photographerList.stream()
                .map(Photographer::getPhotographerId)
                .map(this::findPhotographer)
                .collect(Collectors.toList());
    }
}
