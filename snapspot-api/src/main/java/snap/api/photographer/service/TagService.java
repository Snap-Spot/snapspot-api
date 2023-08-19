package snap.api.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.Tag;
import snap.domains.photographer.service.PhotographerTagDomainService;
import snap.domains.photographer.service.TagDomainService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final PhotographerTagDomainService photographerTagDomainService;
    private final TagDomainService tagDomainService;

    public void createTag(Photographer photographer, String tags) {
        Arrays.stream(tags.split("#")).map(String::trim)
                .forEach(tag -> photographerTagDomainService.createTag(photographer, tag));
    }

    public List<String> findTagStartingWith(String str){
        return tagDomainService.findTagStartingWith(str).stream().map(Tag::getTag).collect(Collectors.toList());
    }
}
