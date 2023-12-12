package comm.repository;

import comm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    //checks if a userName already exists
    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);

    //searches  a list of a users  whose  username contain specified query
    List<User> findByUserNameContainingIgnoreCase(String query);

    // retrieves all  by their user id
    Optional<Object> findAllByUserId(Long userId);
}
