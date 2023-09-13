package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerImage;
import snap.domains.photographer.repository.PhotographerImageRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerImageDomainService {
    private final PhotographerImageRepository imageRepository;

    public void updatePhotographerImage(Photographer photographer, List<String> photographerImages) {
        List<PhotographerImage> oldList = imageRepository.findAllByPhotographer(photographer);
        imageRepository.deleteAll(oldList);

        /*
        photographerImages.forEach(image -> imageRepository.save(
                PhotographerImage.builder().photographer(photographer).image(image).build()
        ));

         */
    }

    public void createPhotographer(Photographer photographer) {
        imageRepository.save(
                PhotographerImage.builder().photographer(photographer).build()
        );
    }
}
