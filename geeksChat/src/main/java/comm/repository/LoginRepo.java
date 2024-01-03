package comm.repository;

import comm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepo extends JpaRepository<User, Long> {
        /* this method will check if a user with the specified username exist
         * */
        List<User> findByUserName(String userName, Class<User> type);
}
