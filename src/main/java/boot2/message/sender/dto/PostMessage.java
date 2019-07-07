package boot2.message.sender.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class PostMessage {

    private final long fromUserId;
    private final long toUserId;
    private final String text;

    @JsonCreator
    public PostMessage(
            @JsonProperty("fromUserId") long fromUserId,
            @JsonProperty("toUserId") long toUserId,
            @JsonProperty("text") String text
    ) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.text = text;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public String getText() {
        return text;
    }
}
