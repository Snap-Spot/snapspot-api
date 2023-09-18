package snap.domains.heart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.heart.entity.Heart;
import snap.domains.heart.repository.HeartRepository;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.service.PhotographerDomainService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartDomainService {

    private final HeartRepository heartRepository;
    private final PhotographerDomainService photographerDomainService;

    @Transactional(readOnly = true)
    public List<Photographer> findHeartByMember(Member member){
        List<Heart> heartList = heartRepository.findByMember(member);
        return heartList.stream().map(Heart::getPhotographer).toList();
    }

    public Heart createHeart(Member member, Long photographerId){
        Photographer photographer = photographerDomainService.findById(photographerId);
        return heartRepository.save(Heart.builder().member(member).photographer(photographer).build());
    }
}
