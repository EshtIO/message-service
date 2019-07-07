package boot2.message.sender.dao;

import boot2.message.sender.context.DaoContextConfiguration;
import org.jooq.DSLContext;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by EshtIO on 2019-07-07.
 */
@RunWith(SpringRunner.class)
@JooqTest
@ContextConfiguration(classes = {
        DaoContextConfiguration.class
})
public abstract class DaoTestBase {

    @Autowired
    protected DSLContext dsl;

}
