package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Provider;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.repository.PhotographerRepository;

@Service
@RequiredArgsConstructor
public class PhotographerDomainService {

    private final PhotographerRepository photographerRepository;

    @Transactional
    public Photographer createPhotographer(String nickname, String email, String password) {
        return photographerRepository.save(
                Photographer.builder()
                        .email(email).password(password).nickname(nickname)
                        .provider(Provider.SNAP_SPOT)
                        .build()
        );
    }
}
