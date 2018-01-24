package ua.kiyv.training.testingSystem.connection.Jdbc;


import org.apache.commons.dbcp2.BasicDataSource;
import ua.kiyv.training.testingSystem.connection.ConnectionPool;
import ua.kiyv.training.testingSystem.dao.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnectionPool implements ConnectionPool {

    private final String DB_CONFIG_FILENAME = "webProject/config/DBconfig.properties";
    private final String DB_CONFIG_PARAM_URL = "database.url";
    private final String DB_CONFIG_PARAM_DB_NAME = "database.dbName";
    private final String DB_CONFIG_PARAM_USER_NAME = "database.userName";
    private final String DB_CONFIG_PARAM_USER_PASSWORD = "database.userPassword";
    private final String DB_CONFIG_PARAM_DRIVER = "database.driver";
    private final String DB_CONFIG_PARAM_MAX_CONNECTIONS = "database.maxConnections";
    private final String DB_CONFIG_PARAM_CONNECTION_PROPERITES="database.connectionProperties";

    private static JdbcConnectionPool instance = new JdbcConnectionPool();

    private BasicDataSource connectionPool;

    {
        Properties props = new Properties();
        connectionPool = null;
        try (InputStream is =
                JdbcConnectionPool.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILENAME)){
            props.load(is);
            String dbUrl = props.getProperty(DB_CONFIG_PARAM_URL) +"/"+ props.getProperty(DB_CONFIG_PARAM_DB_NAME);
            connectionPool = new BasicDataSource();
            connectionPool.setDriverClassName(props.getProperty(DB_CONFIG_PARAM_DRIVER));
            connectionPool.setUrl(dbUrl);
            connectionPool.setUsername(props.getProperty(DB_CONFIG_PARAM_USER_NAME));
            connectionPool.setPassword(props.getProperty(DB_CONFIG_PARAM_USER_PASSWORD));
            connectionPool.setMaxTotal(Integer.parseInt(props.getProperty(DB_CONFIG_PARAM_MAX_CONNECTIONS)));
            connectionPool.setConnectionProperties(props.getProperty(DB_CONFIG_PARAM_CONNECTION_PROPERITES));
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    private JdbcConnectionPool() {}

    public static JdbcConnectionPool getInstance() {
        return instance;
    }

    @Override
    public JdbcDaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(connectionPool.getConnection());
        } catch (SQLException e) {
            throw new DaoException("Can't get dao connection", e);
        }
    }
}