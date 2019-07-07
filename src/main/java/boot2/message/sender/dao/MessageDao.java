package boot2.message.sender.dao;

import boot2.message.sender.exception.NotFoundException;
import boot2.message.sender.jooq.tables.records.MessagesRecord;
import org.jooq.DSLContext;
import org.jooq.Record1;

import static boot2.message.sender.jooq.tables.Messages.MESSAGES;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class MessageDao {

    private final DSLContext dsl;

    public MessageDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    public MessagesRecord insert(MessagesRecord record) {
        return dsl.insertInto(MESSAGES)
                .set(record)
                .returning()
                .fetchOne();
    }

    public int getMessageStatus(long messageId) {
        Record1<Integer> record = dsl.select(MESSAGES.STATUS)
                .from(MESSAGES)
                .where(MESSAGES.ID.eq(messageId))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException("Message with id " + messageId + " not found");
        }
        return record.value1();
    }

}
