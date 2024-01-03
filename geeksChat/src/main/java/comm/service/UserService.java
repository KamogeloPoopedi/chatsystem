package comm.service;

import comm.Dto.RegisterDto;
import comm.entity.Contact;
import comm.entity.User;
import comm.repository.ContactRepo;
import comm.repository.LoginRepo;
import comm.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


//    public User loginUser(String username, String password) {//method to log in after registering
//        User user = loginRepo.findByUserName(username, User.class);
//        if (user != null) {
//            if (passwordEncoder.matches(password, user.getPassword()))
//                return user; // Successful login
//        }
//        return null; // Login failed
//    }
public User loginUser(String username, String password) {
    List<User> userList = loginRepo.findByUserName(username, User.class);

    if (userList != null && !userList.isEmpty()) {
        // Check each user to find the one with matching password
        for (User user : userList) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user; // Successful login
            }
        }
    }

    return null; // Login failed
}


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }//retrieve all users

    public User getUserById(Long userId) {// this method retrieves a user based on userID
        Optional<User> optionalUser = userRepo.findById(userId);
        return optionalUser.orElse(null);
    }



    public List<User> searchUsers(String query) {
        return userRepo.findByUserNameContainingIgnoreCase(query);
    }

    public void addContact(Long UserID, Long contactUserID) {// This method  add a contact for a given user
        User user = (User) userRepo.findAllByUserId(UserID)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));//retrieving a user with a given userID from userRepo
        User contactUser = (User) userRepo.findAllByUserId(contactUserID)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));////retrieving a user with a given contactuserID from userRepo

        if (contactRepo.existsByUserIDAndContactUserID(user, contactUser)) {//checking if there's a relationship between the user and contactUser
            throw new RuntimeException("Contact already exists");
        }else {
            System.out.println("Checking something");//used for checking if my code reaches here
        }
        System.out.println("adding");
        // Creating  and saving  the contact to a user
        Contact newContact = new Contact();
        newContact.setUserID(user);
        newContact.setContactUserID(contactUser);
//
        contactRepo.save(newContact);
    }

}





