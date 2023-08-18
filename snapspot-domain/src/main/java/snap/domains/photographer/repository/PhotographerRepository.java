package snap.domains.photographer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.photographer.entity.Photographer;

import java.util.Optional;


public interface PhotographerRepository extends JpaRepository<Photographer, Long> {
    Optional<Photographer> findByMember_Email(String email);
}
