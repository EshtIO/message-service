package boot2.message.sender.context;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * Created by EshtIO on 2019-07-07.
 */
@Configuration
public class JooqContextConfiguration {

    private final DataSource dataSource;

    public JooqContextConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public DefaultDSLContext dsl() {
        return new DefaultDSLContext(jooqConfig());
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }

    private DefaultConfiguration jooqConfig() {
        DefaultConfiguration result = new DefaultConfiguration();
        result.set(connectionProvider());
        result.set(SQLDialect.POSTGRES_9_5);
        return result;
    }
}
