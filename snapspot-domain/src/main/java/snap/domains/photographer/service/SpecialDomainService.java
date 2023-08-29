package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Special;
import snap.domains.photographer.repository.SpecialRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecialDomainService {

    private final SpecialRepository specialRepository;

    @Transactional(readOnly = true)
    public List<Special> findSpecialListByPhotographerId(Long photographerId){
        return specialRepository.findAllByPhotographer_PhotographerId(photographerId);
    }
}
