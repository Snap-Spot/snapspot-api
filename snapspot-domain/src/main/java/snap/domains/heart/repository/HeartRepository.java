package snap.domains.heart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.heart.entity.Heart;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> findByMember(Member member);

    Optional<Heart> findByMemberAndPhotographer(Member member, Photographer photographer);
}
