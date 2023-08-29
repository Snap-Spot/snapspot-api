package snap.api.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerTag;
import snap.domains.photographer.entity.Tag;
import snap.domains.photographer.service.PhotographerTagDomainService;
import snap.domains.photographer.service.TagDomainService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final PhotographerTagDomainService photographerTagDomainService;
    private final TagDomainService tagDomainService;

    public List<String> createTag(Photographer photographer, String tags) {
        return Arrays.stream(tags.split("#"))
                .map(tag -> photographerTagDomainService.createTag(photographer, tag.trim()).getTag().getTag())
                .collect(Collectors.toList());
    }

    public List<String> findTagStartingWith(String str){
        return tagDomainService.findTagStartingWith(str).stream().map(Tag::getTag).collect(Collectors.toList());
    }
}
