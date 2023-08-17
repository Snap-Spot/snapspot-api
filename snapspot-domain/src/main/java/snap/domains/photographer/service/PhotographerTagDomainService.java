package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.PhotographerTag;
import snap.domains.photographer.repository.PhotographerTagRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerTagDomainService {

    private final PhotographerTagRepository photographerTagRepository;

    @Transactional(readOnly = true)
    public List<PhotographerTag> findTagList(Long photographerId){
        return photographerTagRepository.findAllByPhotographer_PhotographerId(photographerId);
    }
}
