package snap.api.heart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.heart.dto.HeartSuccessResponseDto;
import snap.api.photographer.dto.response.PhotographerResponseDto;
import snap.domains.heart.service.HeartDomainService;
import snap.domains.member.entity.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartDomainService heartDomainService;

    public List<PhotographerResponseDto> heartListByMember(Member member){
        return heartDomainService.findHeartByMember(member).stream().map(PhotographerResponseDto::new).toList();
    }

    public HeartSuccessResponseDto heartCreate(Member member, Long photographerId){
        return new HeartSuccessResponseDto(heartDomainService.createHeart(member, photographerId));
    }
}
