package comm.repository;

import comm.entity.ChatThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharThreadRepo extends JpaRepository<ChatThread, Long> {

}
