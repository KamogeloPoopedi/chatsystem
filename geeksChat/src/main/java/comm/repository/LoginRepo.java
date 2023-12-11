package comm.repository;

import comm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends JpaRepository<User, Long> {

        User  findByUserName(String userName, Class<User> type);
}
