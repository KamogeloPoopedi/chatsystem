package comm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void  sendMessage(String destination, Object payload){
        simpMessagingTemplate.convertAndSend(destination,
                payload);
    }
}
