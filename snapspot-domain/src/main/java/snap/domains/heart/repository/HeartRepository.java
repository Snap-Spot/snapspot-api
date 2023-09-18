package snap.domains.heart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.heart.entity.Heart;
import snap.domains.member.entity.Member;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> findByMember(Member member);
}
