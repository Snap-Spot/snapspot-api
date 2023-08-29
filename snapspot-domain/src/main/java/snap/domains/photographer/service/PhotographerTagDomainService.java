package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerTag;
import snap.domains.photographer.entity.Tag;
import snap.domains.photographer.repository.PhotographerTagRepository;
import snap.domains.photographer.repository.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerTagDomainService {

    private final PhotographerTagRepository photographerTagRepository;
    private final TagRepository tagRepository;

    public PhotographerTag createTag(Photographer photographer, String tag){
        Tag entity;
        if(tagRepository.existsByTag(tag))
            entity = tagRepository.findByTag(tag);
        else
            entity = tagRepository.save(new Tag(tag));
        return photographerTagRepository.save(new PhotographerTag(photographer, entity));
    }

    @Transactional(readOnly = true)
    public List<Photographer> findPhotographerListByTag(String tag){
        Tag entity = tagRepository.findByTag(tag);
        List<PhotographerTag> photographerTagList = photographerTagRepository.findAllByTag(entity);
        return photographerTagList.stream().map(PhotographerTag::getPhotographer)
                .collect(Collectors.toList());
    }
}
