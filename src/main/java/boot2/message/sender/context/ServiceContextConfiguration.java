package boot2.message.sender.context;

import boot2.message.sender.dao.MessageDao;
import boot2.message.sender.dao.UserDao;
import boot2.message.sender.service.MessageService;
import boot2.message.sender.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by EshtIO on 2019-07-07.
 */
@Configuration
public class ServiceContextConfiguration {

    @Bean
    public UserService userService(UserDao dao) {
        return new UserService(dao);
    }

    @Bean
    public MessageService messageService(MessageDao dao) {
        return new MessageService(dao);
    }

}
