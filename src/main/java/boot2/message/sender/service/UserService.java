package boot2.message.sender.service;

import boot2.message.sender.dao.UserDao;
import boot2.message.sender.dto.PostUser;
import boot2.message.sender.dto.User;
import boot2.message.sender.jooq.tables.records.UsersRecord;


/**
 * Created by EshtIO on 2019-07-07.
 */
public class UserService {

    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public User saveUser(PostUser user) {
        UsersRecord insert = new UsersRecord();
        insert.setName(user.getName());

        UsersRecord inserted = dao.insert(insert);

        return new User(inserted.getId(), inserted.getName());
    }

}
