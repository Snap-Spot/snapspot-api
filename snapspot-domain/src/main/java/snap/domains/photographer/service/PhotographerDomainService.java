package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.repository.PhotographerRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerDomainService {

    private final PhotographerRepository photographerRepository;


    public Photographer createPhotographer(Member member) {
        return photographerRepository.save(
                Photographer.builder().member(member).build()
        );
    }

    @Transactional(readOnly = true)
    public Photographer findByEmail(String email) {
        return photographerRepository.findByMember_Email(email)
                .orElseThrow(() -> new RuntimeException("올바르지 않은 이메일입니다."));
    }

    @Transactional(readOnly = true)
    public Photographer findById(Long id) {
        return photographerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID를 가진 사진 작가를 찾을 수 없습니다."));
    }
}
