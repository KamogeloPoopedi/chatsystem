package comm.controller;

import comm.Dto.MessageDto;
import comm.entity.ChatThread;
import comm.service.ChatService;
import comm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    private  MessageService messageService;
    @Autowired
    private  ChatService chatService;

    public MessageController(MessageService messageService, ChatService chatService) {
        this.messageService = messageService;
        this.chatService = chatService;
    }


    @MessageMapping(value = "/send")
    public void sendMessage(@Payload MessageDto messageDto){
        ChatThread saveMessage = chatService.saveChats(messageDto);
        System.out.println(saveMessage);
        String destination = "/topic/chat/" + saveMessage.getReceiver().getUserId();
        messageService.sendMessage(destination, saveMessage);
    }
}
