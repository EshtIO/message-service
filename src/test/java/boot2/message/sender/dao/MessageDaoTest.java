package boot2.message.sender.dao;

import boot2.message.sender.jooq.tables.records.MessagesRecord;
import boot2.message.sender.jooq.tables.records.UsersRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static boot2.message.sender.jooq.tables.Messages.MESSAGES;
import static boot2.message.sender.jooq.tables.Users.USERS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class MessageDaoTest extends DaoTestBase {

    @Autowired
    private MessageDao dao;

    private UsersRecord fromUser;
    private UsersRecord toUser;

    @Before
    public void before() {
        fromUser = dsl.insertInto(USERS)
                .set(new UsersRecord().value2("from-user"))
                .returning()
                .fetchOne();

        toUser = dsl.insertInto(USERS)
                .set(new UsersRecord().value2("to-user"))
                .returning()
                .fetchOne();
    }

    @Test
    public void saveMessage() {
        MessagesRecord record = new MessagesRecord();
        record.setText("message-text");
        record.setFromUserId(fromUser.getId());
        record.setToUserId(toUser.getId());
        record.setStatus(1);

        MessagesRecord actual = dao.insert(record);
        MessagesRecord expected = dsl.selectFrom(MESSAGES)
                .where(MESSAGES.ID.eq(actual.getId()))
                .fetchOne();

        assertThat(expected).isEqualTo(actual);

        expected.delete();
    }

    @Test
    public void getMessageStatus() {
        int expectedStatus = 2;

        MessagesRecord record = dsl.newRecord(MESSAGES);
        record.setText("text");
        record.setFromUserId(fromUser.getId());
        record.setToUserId(toUser.getId());
        record.setStatus(expectedStatus);
        record.insert();

        assertThat(dao.getMessageStatus(record.getId())).isEqualTo(expectedStatus);

        record.delete();
    }

    @After
    public void after() {
        dsl.batchDelete(fromUser, toUser).execute();
    }

}
