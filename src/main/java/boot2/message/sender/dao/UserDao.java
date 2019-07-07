package boot2.message.sender.dao;

import boot2.message.sender.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;

import static boot2.message.sender.jooq.tables.Users.USERS;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class UserDao {

    private final DSLContext dsl;

    public UserDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    public UsersRecord insert(UsersRecord record) {
        return dsl.insertInto(USERS)
                .set(record)
                .returning()
                .fetchOne();
    }

}
