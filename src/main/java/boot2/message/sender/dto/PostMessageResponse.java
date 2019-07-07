package boot2.message.sender.dto;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class PostMessageResponse {

    private final long id;
    private final long fromUserId;
    private final long toUserId;
    private final MessageStatus status;

    public PostMessageResponse(long id, long fromUserId, long toUserId, MessageStatus status) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public MessageStatus getStatus() {
        return status;
    }

}
