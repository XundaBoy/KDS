package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import java.util.Date;
import app.entity.Message;
import app.repository.MessageRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;


@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private MessageRepository messageRepository;
	
	 @MessageMapping("/messages")
	    public void handleMessage(Message message) {
	        message.setTimestamp(new Date());
	        messageRepository.save(message);
	        template.convertAndSend("/channel/chat/" + message.getChannel(), message);
	    }
}
