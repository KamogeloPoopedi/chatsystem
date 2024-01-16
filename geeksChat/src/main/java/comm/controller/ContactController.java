package comm.controller;

import comm.entity.Contact;
import comm.entity.User;
import comm.repository.UserRepo;
import comm.service.ChatService;
import comm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @PostMapping("/{userId}/add/{contactUserID}")
    public ResponseEntity<?> addContact(@PathVariable Long userId, @PathVariable Long contactUserID) {
        System.out.println(userId +" " + contactUserID);
        userService.addContact(userId, contactUserID);
        return ResponseEntity.ok(Map.of("message", "Contact added successfully"));
    }
    @GetMapping("/list")
    public ResponseEntity<List<User>> getContactList(@RequestParam Long userId) {
        List<User> contacts = chatService.getContacts(userId);
        return ResponseEntity.ok(contacts);
    }




}
