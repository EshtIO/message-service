package boot2.message.sender.dao;

import boot2.message.sender.jooq.tables.records.UsersRecord;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static boot2.message.sender.jooq.tables.Users.USERS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by EshtIO on 2019-07-07.
 */
public class UserDaoTest extends DaoTestBase {

    @Autowired
    private UserDao dao;

    @Test
    public void insertRecord() {
        UsersRecord insert = new UsersRecord();
        insert.setName("user-name");

        UsersRecord result = dao.insert(insert);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(insert.getName());

        UsersRecord record = dsl.selectFrom(USERS)
                .where(USERS.ID.eq(result.getId()))
                .fetchOne();
        assertThat(record.getName()).isEqualTo(insert.getName());

        record.delete();
    }

}
