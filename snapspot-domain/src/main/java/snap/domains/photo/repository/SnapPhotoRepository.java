package snap.domains.photo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.member.entity.Member;
import snap.domains.photo.entity.SnapPhoto;

public interface SnapPhotoRepository extends JpaRepository<SnapPhoto, Long> {

    Page<SnapPhoto> findAllByMember(Member member, Pageable pageable);
}
