package boot2.message.sender.service;

import boot2.message.sender.dto.PostUser;
import boot2.message.sender.dto.User;
import boot2.message.sender.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;

import static boot2.message.sender.jooq.tables.Users.USERS;


/**
 * Created by EshtIO on 2019-07-07.
 */
public class UserService {

    private final DSLContext dsl;

    public UserService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public User saveUser(PostUser user) {
        UsersRecord insert = new UsersRecord();
        insert.setName(user.getName());

        UsersRecord inserted = dsl.insertInto(USERS)
                .set(insert)
                .returning()
                .fetchOne();

        return new User(inserted.getId(), inserted.getName());
    }

}
