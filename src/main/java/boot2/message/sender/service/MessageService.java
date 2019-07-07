package boot2.message.sender.service;

import boot2.message.sender.dao.MessageDao;
import boot2.message.sender.dto.message.MessageStatusResponse;
import boot2.message.sender.dto.message.PostMessageResponse;
import boot2.message.sender.dto.message.MessageStatus;
import boot2.message.sender.dto.message.PostMessage;
import boot2.message.sender.jooq.tables.records.MessagesRecord;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class MessageService {

    private static final int IN_PROGRESS = 1;
    private static final int SUCCESS = 2;

    private final MessageDao dao;

    public MessageService(MessageDao dao) {
        this.dao = dao;
    }

    public PostMessageResponse saveMessage(PostMessage message) {
        MessagesRecord data = new MessagesRecord();
        data.setFromUserId(message.getFromUserId());
        data.setToUserId(message.getToUserId());
        data.setStatus(IN_PROGRESS);
        data.setText(message.getText());

        return transform(dao.insert(data));
    }

    public MessageStatusResponse getStatus(long id) {
        return new MessageStatusResponse(mapStatus(dao.getMessageStatus(id)));
    }

    private static PostMessageResponse transform(MessagesRecord record) {
        return new PostMessageResponse(
                record.getId(),
                record.getFromUserId(),
                record.getToUserId(),
                mapStatus(record.getStatus())
        );
    }

    private static MessageStatus mapStatus(int status) {
        switch (status) {
            case IN_PROGRESS:
                return MessageStatus.IN_PROGRESS;
            case SUCCESS:
                return MessageStatus.SENT;
            default:
                throw new IllegalArgumentException("Illegal status code " + status);
        }
    }

}
