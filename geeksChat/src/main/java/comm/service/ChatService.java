package comm.service;

import comm.entity.Contact;
import comm.entity.User;
import comm.repository.ContactRepo;
import comm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;
    public List<User> getContacts(Long userId) {
        System.out.println("Fetching contacts for user ID: " + userId);
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Contact> userContact = contactRepo.findByUserID(user);
            return userContact.stream()
                    .map(Contact::getContactUserID)
                    .collect(Collectors.toList());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User with  found with ID: " + userId);
        }
    }
}
