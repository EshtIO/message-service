package boot2.message.sender.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class PostUser {

    private final String name;

    @JsonCreator
    public PostUser(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
