package snap.domains.message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snap.domains.message.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
