package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Sns;
import snap.domains.photographer.repository.SnsRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SnsDomainService {

    private final SnsRepository snsRepository;

    @Transactional(readOnly = true)
    public List<Sns> findSnsListByPhotographerId(Long photographerId){
        return snsRepository.findAllByPhotographer_PhotographerId(photographerId);
    }
}
