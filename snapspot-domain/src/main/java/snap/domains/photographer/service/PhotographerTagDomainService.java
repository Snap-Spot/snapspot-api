package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerTag;
import snap.domains.photographer.repository.PhotographerTagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerTagDomainService {

    private final PhotographerTagRepository photographerTagRepository;

    @Transactional(readOnly = true)
    public List<Photographer> findPhotographerListByTag(String tag){

        return photographerTagRepository.findAllByTag1OrTag2OrTag3(tag, tag, tag)
                .stream().map(PhotographerTag::getPhotographer).toList();

    }
    public void createPhotographer(Photographer photographer) {
        photographerTagRepository.save(
                PhotographerTag.builder().photographer(photographer).build()
        );
    }
}
