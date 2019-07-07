package boot2.message.sender.context;

import boot2.message.sender.service.MessageService;
import boot2.message.sender.service.UserService;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by EshtIO on 2019-07-07.
 */
@Configuration
public class ServiceContextConfiguration {

    @Bean
    public UserService userService(DSLContext dsl) {
        return new UserService(dsl);
    }

    @Bean
    public MessageService messageService(DSLContext dsl) {
        return new MessageService(dsl);
    }

}
