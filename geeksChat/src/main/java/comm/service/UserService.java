package comm.service;

import comm.Dto.RegisterDto;
import comm.entity.Contact;
import comm.entity.User;
import comm.repository.ContactRepo;
import comm.repository.LoginRepo;
import comm.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    public Object getUserById;
    private LoginRepo loginRepo;
    private UserRepo userRepo;
    private ContactRepo contactRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(LoginRepo loginRepo, UserRepo userRepo, ContactRepo contactRepo, PasswordEncoder passwordEncoder) {
        this.loginRepo = loginRepo;
        this.userRepo = userRepo;
        this.contactRepo = contactRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserService(Object getUserById) {
        this.getUserById = getUserById;
    }


    public User loginUser(String username, String password) {
        User user = loginRepo.findByUserName(username, User.class);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword()))
                return user; // Successful login
        }
        return null; // Login failed
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        return optionalUser.orElse(null);
    }



    public List<User> searchUsers(String query) {
        return userRepo.findByUserNameContainingIgnoreCase(query);
    }

    public void addContact(Long UserID, Long contactUserID) {
        User user = (User) userRepo.findAllByUserId(UserID).orElseThrow(() -> new EntityNotFoundException("User not found"));
        User contactUser = (User) userRepo.findAllByUserId(contactUserID).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (contactRepo.existsByUserIDAndContactUserID(user, contactUser)) {
            throw new RuntimeException("Contact already exists");
        }else {
            System.out.println("Checking something");
        }
        System.out.println("adding");
        // Create and save the contact relationship
        Contact newContact = new Contact();
        newContact.setUserID(user);
        newContact.setContactUserID(contactUser);
//
        contactRepo.save(newContact);
    }

}





