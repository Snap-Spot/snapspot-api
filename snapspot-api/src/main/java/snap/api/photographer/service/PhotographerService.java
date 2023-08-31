package snap.api.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.api.photographer.dto.response.PhotographerSearchResponseDto;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.PhotographerAreaDomainService;
import snap.domains.photographer.service.PhotographerDomainService;
import snap.domains.photographer.service.PhotographerTagDomainService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotographerService {

    private final PhotographerDomainService photographerDomainService;
    private final PhotographerAreaDomainService photographerAreaDomainService;
    private final PhotographerTagDomainService photographerTagDomainService;


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

    public List<PhotographerResponseDto> findAllPhotographers(Pageable pageable){
        return photographerDomainService.findAllPhotographers(pageable)
                .map(PhotographerResponseDto::new)
                .getContent();
    }
}
