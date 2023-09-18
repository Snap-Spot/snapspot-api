package snap.domains.heart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.heart.entity.Heart;
import snap.domains.heart.repository.HeartRepository;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartDomainService {

    private final HeartRepository heartRepository;

    @Transactional(readOnly = true)
    public List<Photographer> findHeartByMember(Member member){
        List<Heart> heartList = heartRepository.findByMember(member);
        return heartList.stream().map(Heart::getPhotographer).toList();
    }
}
