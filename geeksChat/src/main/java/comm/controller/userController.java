package comm.controller;

import comm.Dto.LoginDto;
import comm.Dto.RegisterDto;
//import comm.entity.Contact;
import comm.entity.User;
//import comm.repository.ContactRepo;
import comm.repository.UserRepo;
import comm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class userController {

    private UserService userService;
    private UserRepo userRepo;
//    private ContactRepo contactRepo;
    private PasswordEncoder passwordEncoder;


    public userController() {
    }

    @Autowired
    public userController(UserService userService, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.passwordEncoder= passwordEncoder;

    }

    @GetMapping("/all")//this method retrieves all users
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{userId}")//this method retrieves a user using userId
    public ResponseEntity<Object> getUserByID(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User " + userId + " doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    //handles the post request to register a user to the system
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (!userRepo.existsByUserName(registerDto.getUserName())) {
            String passWordEncoder = passwordEncoder.encode(registerDto.getPassword());
            User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setPassword(passWordEncoder);
            user.setUserName(registerDto.getUserName());
            user.setFName("");
            user.setLName("");
            userRepo.save(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User already exits", HttpStatus.BAD_REQUEST);
        }
    }

    // handles the post request to log in the user
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        User user = userService.loginUser(loginDto.getUserName(), loginDto.getPassword());
        if (user == null) {
            return new ResponseEntity<>("login unsuccessful", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("login successful", HttpStatus.OK);
    }

    //method to handle a get request to search user
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        List<User> users = userService.searchUsers(query);
        return ResponseEntity.ok(users);


    }

    // method for handling post request to  add  a user as contact
    @PostMapping("/{userId}/add/{contactId}")
    public ResponseEntity<String> addContact(@PathVariable Long userId, @PathVariable Long contactId) {
        System.out.println(userId +" " + contactId);
        userService.addContact(userId, contactId);
        return ResponseEntity.ok("Contact added successfully");
    }
}
