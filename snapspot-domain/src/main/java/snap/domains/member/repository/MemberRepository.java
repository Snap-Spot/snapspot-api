package snap.domains.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snap.domains.member.entity.Member;
import snap.enums.Provider;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Boolean existsByEmail(String email);

    Optional<Member> findByEmailAndProvider(String email, Provider provider);

    List<Member> findAllByNicknameContaining(String nickname);
}
