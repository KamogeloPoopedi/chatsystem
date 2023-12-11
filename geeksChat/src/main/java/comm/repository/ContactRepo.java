package comm.repository;

import comm.entity.Contact;
import comm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

    boolean existsByUserIDAndContactUserID(User user, User contactUser);

}
