package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.member.entity.Provider;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.repository.PhotographerRepository;

@Service
@RequiredArgsConstructor
public class PhotographerDomainService {

    private final PhotographerRepository photographerRepository;

    @Transactional
    public Photographer createPhotographer(Member member) {
        return photographerRepository.save(
                Photographer.builder().member(member).build()
        );
    }
}
