package boot2.message.sender.dto.user;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class PostUserResponse {

    private final long id;
    private final String name;

    public PostUserResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
