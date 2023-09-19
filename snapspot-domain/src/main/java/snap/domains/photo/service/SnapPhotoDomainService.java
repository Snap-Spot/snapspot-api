package snap.domains.photo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.photo.entity.SnapPhoto;
import snap.domains.photo.repository.SnapPhotoRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class SnapPhotoDomainService {

    private final SnapPhotoRepository snapPhotoRepository;

    public SnapPhoto createPhoto(Member member, String imageUrl){
        return snapPhotoRepository.save(SnapPhoto.builder().member(member).imageUrl(imageUrl).build());
    }
}
