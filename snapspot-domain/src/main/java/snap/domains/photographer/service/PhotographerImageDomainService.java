package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerImage;
import snap.domains.photographer.repository.PhotographerImageRepository;
import snap.dto.request.ImageReq;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerImageDomainService {
    private final PhotographerImageRepository imageRepository;

    public void createPhotographer(Photographer photographer) {
        imageRepository.save(
                PhotographerImage.builder().photographer(photographer).build()
        );
    }

    public void updatePhotographerImage(Photographer photographer, ImageReq image) {
        photographer.getImages().update(image);
    }
}
