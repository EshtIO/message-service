package boot2.message.sender.service;

import boot2.message.sender.dto.Message;
import boot2.message.sender.dto.MessageStatus;
import boot2.message.sender.dto.PostMessage;
import boot2.message.sender.jooq.tables.records.MessagesRecord;
import org.jooq.DSLContext;

import static boot2.message.sender.jooq.tables.Messages.MESSAGES;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class MessageService {

    private static final int IN_PROGRESS = 1;
    private static final int SUCCESS = 2;

    private final DSLContext dsl;

    public MessageService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Message saveMessage(PostMessage message) {
        MessagesRecord insert = new MessagesRecord();
        insert.setFromUserId(message.getFromUserId());
        insert.setToUserId(message.getToUserId());
        insert.setStatus(IN_PROGRESS);
        insert.setText(message.getText());

        MessagesRecord record = dsl.insertInto(MESSAGES)
                .set(insert)
                .returning()
                .fetchOne();

        return new Message(
                record.getId(), record.getFromUserId(), record.getToUserId(), mapStatus(record.getStatus()));
    }

    private static MessageStatus mapStatus(int status) {
        switch (status) {
            case IN_PROGRESS:
                return MessageStatus.IN_PROGRESS;
            case SUCCESS:
                return MessageStatus.SUCCESS;
            default:
                throw new IllegalArgumentException("Illegal status code " + status);
        }
    }

}
