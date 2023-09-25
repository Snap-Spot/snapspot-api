package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.member.repository.MemberRepository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.repository.PhotographerRepository;
import snap.dto.request.PhotographerFilterReq;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerDomainService {

    private final MemberRepository memberRepository;
    private final PhotographerRepository photographerRepository;
    private final PhotographerImageDomainService photographerImageDomainService;
    private final SnsDomainService snsDomainService;
    private final PhotographerTagDomainService tagDomainService;


    public Photographer createPhotographer(Member member) {
        Photographer photographer = photographerRepository.save(
                Photographer.builder().member(member).build()
        );
        snsDomainService.createSns(photographer);
        photographerImageDomainService.createPhotographer(photographer);
        tagDomainService.createPhotographer(photographer);
        return photographer;
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

    @Transactional(readOnly = true)
    public List<Photographer> findByNickname(String nickname){
        List<Member> memberList = memberRepository.findAllByNicknameContaining(nickname);
        return memberList.stream().map(photographerRepository::findByMember).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Photographer> findAllToList(){
        return photographerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Photographer> findAllByFilter(PhotographerFilterReq filterReq){
        return photographerRepository.searchAll(filterReq);
    }

    public void updatePhotographer(Photographer photographer, String nickname, String profileImage,
                                           String paymentImage, Long lowestPay, String bio){
        photographer.updatePhotographer(nickname, profileImage, paymentImage, lowestPay, bio);
    }

    public List<Photographer> findRandom() {
        Long size = photographerRepository.countBy();
        int idx = (int)(Math.random() * size);
        Page<Photographer> photographers = photographerRepository.findAll(PageRequest.of(1, 3));
        log.info(String.valueOf(photographers.stream().collect(Collectors.toList()).size()));
        return photographers.stream().toList();
    }
}
