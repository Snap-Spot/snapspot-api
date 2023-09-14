package snap.api.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.photographer.dto.response.TagsDto;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.PhotographerTagDomainService;


import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final PhotographerTagDomainService photographerTagDomainService;


}
