package comm.controller;

import comm.entity.User;
import comm.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getContactList(@RequestParam Long userId) {
        List<User> contacts = chatService.getContacts(userId);
        return ResponseEntity.ok(contacts);
    }
}
