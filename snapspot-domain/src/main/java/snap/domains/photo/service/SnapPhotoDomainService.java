package snap.domains.photo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.member.entity.Member;
import snap.domains.photo.entity.SnapPhoto;
import snap.domains.photo.repository.SnapPhotoRepository;
import snap.domains.photographer.entity.Photographer;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SnapPhotoDomainService {

    private final SnapPhotoRepository snapPhotoRepository;

    public SnapPhoto createPhoto(Member member, String imageUrl, LocalDateTime photoDate, String location,
                                 Photographer photographer, String tag1, String tag2, String tag3){
        return snapPhotoRepository.save(
                SnapPhoto.builder()
                        .member(member)
                        .imageUrl(imageUrl)
                        .photoDate(photoDate)
                        .location(location)
                        .photographer(photographer)
                        .tag1(tag1)
                        .tag2(tag2)
                        .tag3(tag3)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public List<SnapPhoto> findPhotoListByMember(Member member) {
        return snapPhotoRepository.findAllByMember(member);
    }
}
