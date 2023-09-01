package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.Sns;
import snap.domains.photographer.repository.SnsRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class SnsDomainService {
    private final SnsRepository snsRepository;

    public void updateSns(Photographer photographer, String instagram, String twitter,
                          String kakaoChannel, String naverBlog, String homepage){
        snsRepository.save(
                Sns.builder()
                        .photographer(photographer)
                        .instagram(instagram)
                        .twitter(twitter)
                        .kakaoChannel(kakaoChannel)
                        .naverBlog(naverBlog)
                        .homepage(homepage)
                        .build()
        );
    }
}
