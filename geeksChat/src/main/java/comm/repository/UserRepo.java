package comm.repository;

import comm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);


    List<User> findByUserNameContainingIgnoreCase(String query);

    Optional<Object> findAllByUserId(Long userId);
}
