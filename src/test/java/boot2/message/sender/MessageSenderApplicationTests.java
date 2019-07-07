package boot2.message.sender;

import boot2.message.sender.dto.*;
import boot2.message.sender.jooq.tables.records.MessagesRecord;
import boot2.message.sender.jooq.tables.records.UsersRecord;
import boot2.message.sender.service.MessageService;
import boot2.message.sender.service.UserService;
import org.jooq.DSLContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static boot2.message.sender.jooq.tables.Messages.MESSAGES;
import static boot2.message.sender.jooq.tables.Users.USERS;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSenderApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @Autowired
    private DSLContext dsl;

    @Before
    public void setUp() {
        dsl.deleteFrom(MESSAGES).execute();
        dsl.deleteFrom(USERS).execute();
    }

    @Test
    public void saveUser() {
        PostUser request = new PostUser("user-name");
        User user = userService.saveUser(request);
        assertThat(user.getName()).isEqualTo(request.getName());

        UsersRecord record = dsl.selectFrom(USERS)
                .where(USERS.ID.eq(user.getId()))
                .fetchOne();
        assertThat(record.getId()).isEqualTo(user.getId());
        assertThat(record.getName()).isEqualTo(user.getName());
    }

    @Test
    public void saveMessage() {
        UsersRecord fromUser = dsl.insertInto(USERS)
                .set(new UsersRecord().value2("from-user"))
                .returning()
                .fetchOne();

        UsersRecord toUser = dsl.insertInto(USERS)
                .set(new UsersRecord().value2("to-user"))
                .returning()
                .fetchOne();

        String messageText = "message-text";
        Message message = messageService.saveMessage(
                new PostMessage(fromUser.getId(), toUser.getId(), messageText));
        assertThat(message.getFromUserId()).isEqualTo(fromUser.getId());
        assertThat(message.getToUserId()).isEqualTo(toUser.getId());
        assertThat(message.getStatus()).isEqualTo(MessageStatus.IN_PROGRESS);

        MessagesRecord record = dsl.selectFrom(MESSAGES)
                .where(MESSAGES.ID.eq(message.getId()))
                .fetchOne();
        assertThat(record.getId()).isEqualTo(message.getId());
        assertThat(record.getFromUserId()).isEqualTo(message.getFromUserId());
        assertThat(record.getToUserId()).isEqualTo(message.getToUserId());
        assertThat(record.getText()).isEqualTo(messageText);
        assertThat(record.getStatus()).isEqualTo(1);
    }
}
