package boot2.message.sender.dto;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class MessageStatusResponse {

    private final MessageStatus status;

    public MessageStatusResponse(MessageStatus status) {
        this.status = status;
    }

    public MessageStatus getStatus() {
        return status;
    }
}
