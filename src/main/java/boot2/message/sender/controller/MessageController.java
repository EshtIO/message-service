package boot2.message.sender.controller;

import boot2.message.sender.dto.Message;
import boot2.message.sender.dto.PostMessage;
import boot2.message.sender.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by EshtIO on 2019-07-07.
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Message postMessage(@RequestBody PostMessage message) {
        return messageService.saveMessage(message);
    }

}
