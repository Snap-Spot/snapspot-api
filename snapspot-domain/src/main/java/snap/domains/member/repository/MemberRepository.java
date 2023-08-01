package snap.domains.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
