package comm.repository;

import comm.entity.Contact;
import comm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
    //method to check if the contact record exists with the specified User as
    // a userID and another user as contactUserid
    boolean existsByUserIDAndContactUserID(User user, User contactUser);
    List<Contact> findByUserID(User user);
}
