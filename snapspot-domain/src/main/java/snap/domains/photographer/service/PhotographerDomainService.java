package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.member.repository.MemberRepository;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.repository.PhotographerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotographerDomainService {

    private final PhotographerRepository photographerRepository;
    private final MemberRepository memberRepository;


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

    @Transactional(readOnly = true)
    public List<Photographer> findByNickname(String nickname){
        List<Member> memberList = memberRepository.findAllByNicknameContaining(nickname);
        return memberList.stream().map(photographerRepository::findByMember).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Photographer> findAllPhotographers(Pageable pageable){
        return photographerRepository.findAll(pageable);
    }
}
