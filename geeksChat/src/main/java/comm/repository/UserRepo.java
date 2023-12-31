package comm.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import comm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    //checks if a userName already exists
    boolean existsByUserName(String userName);


    //searches  a list of a users  whose  username contain specified query
    List<User> findByUserNameContainingIgnoreCase(String query);

    // retrieves all  by their user id
    Optional<Object> findAllByUserId(Long userId);
}
