package boot2.message.sender.controller;

import boot2.message.sender.dto.MessageStatusResponse;
import boot2.message.sender.dto.PostMessageResponse;
import boot2.message.sender.dto.PostMessage;
import boot2.message.sender.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public PostMessageResponse postMessage(@RequestBody PostMessage message) {
        return messageService.saveMessage(message);
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public MessageStatusResponse getMessageStatus(@PathVariable("id") long id) {
        return messageService.getStatus(id);
    }

}
