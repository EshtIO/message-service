package boot2.message.sender.context;

import boot2.message.sender.dao.MessageDao;
import boot2.message.sender.dao.UserDao;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by EshtIO on 2019-07-07.
 */
@Configuration
public class DaoContextConfiguration {

    @Bean
    public MessageDao messageDao(DSLContext dslContext) {
        return new MessageDao(dslContext);
    }

    @Bean
    public UserDao userDao(DSLContext dslContext) {
        return new UserDao(dslContext);
    }

}
