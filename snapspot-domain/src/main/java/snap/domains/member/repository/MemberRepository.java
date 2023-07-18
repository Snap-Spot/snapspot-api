package snap.domains.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
