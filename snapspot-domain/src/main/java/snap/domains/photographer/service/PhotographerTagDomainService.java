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

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerTagDomainService {

    private final PhotographerTagRepository photographerTagRepository;
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<PhotographerTag> findTagList(Long photographerId){
        return photographerTagRepository.findAllByPhotographer_PhotographerId(photographerId);
    }

    public void createTag(Photographer photographer, String tag){
        Tag entity = tagRepository.save(new Tag(tag));
        photographerTagRepository.save(new PhotographerTag(photographer, entity));
    }
}
