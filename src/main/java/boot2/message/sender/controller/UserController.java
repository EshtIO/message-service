package boot2.message.sender.controller;

import boot2.message.sender.dto.user.PostUser;
import boot2.message.sender.dto.user.PostUserResponse;
import boot2.message.sender.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by EshtIO on 2019-07-07.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            path = "/",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public PostUserResponse postUser(@RequestBody PostUser insert) {
        return userService.saveUser(insert);
    }

}
