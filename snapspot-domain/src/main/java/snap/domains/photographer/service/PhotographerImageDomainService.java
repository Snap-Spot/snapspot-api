package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.PhotographerImage;
import snap.domains.photographer.repository.PhotographerImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerImageDomainService {
    private final PhotographerImageRepository imageRepository;

    public void updatePhotographerImage(Photographer photographer, List<String> photographerImages){
        photographerImages.stream()
                .map(image -> imageRepository.save(
                        PhotographerImage.builder()
                                .photographer(photographer)
                                .image(image)
                                .build()
                ))
                .collect(Collectors.toList());
    }
}
