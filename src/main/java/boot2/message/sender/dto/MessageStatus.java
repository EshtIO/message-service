package boot2.message.sender.dto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by EshtIO on 2019-07-07.
 */
public enum MessageStatus {

    IN_PROGRESS("in-progress"),
    SUCCESS("success");

    private final String code;

    MessageStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

}