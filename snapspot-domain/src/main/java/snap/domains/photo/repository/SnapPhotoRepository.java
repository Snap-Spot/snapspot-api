package snap.domains.photo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.member.entity.Member;
import snap.domains.photo.entity.SnapPhoto;

import java.util.List;

public interface SnapPhotoRepository extends JpaRepository<SnapPhoto, Long> {

    List<SnapPhoto> findAllByMember(Member member);
}
