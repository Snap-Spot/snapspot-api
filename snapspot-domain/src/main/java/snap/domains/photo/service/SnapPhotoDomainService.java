package snap.domains.photo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.photo.entity.SnapPhoto;
import snap.domains.photo.repository.SnapPhotoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SnapPhotoDomainService {

    private final SnapPhotoRepository snapPhotoRepository;

    public SnapPhoto createPhoto(Member member, String imageUrl, LocalDateTime photoDate, String location,
                                 String photographerName, String tag){
        return snapPhotoRepository.save(
                SnapPhoto.builder()
                        .member(member)
                        .imageUrl(imageUrl)
                        .photoDate(photoDate)
                        .location(location)
                        .photographerName(photographerName)
                        .tag(tag)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<SnapPhoto> findPhotoListByMember(Member member) {
        return snapPhotoRepository.findAllByMember(member);
    }
}
