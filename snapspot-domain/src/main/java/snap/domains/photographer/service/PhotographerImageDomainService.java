package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.PhotographerImage;
import snap.domains.photographer.repository.PhotographerImageRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerImageDomainService {

    private final PhotographerImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<PhotographerImage> findImageListByPhotographerId(Long photographerId){
        return imageRepository.findAllByPhotographer_PhotographerId(photographerId);
    }
}
