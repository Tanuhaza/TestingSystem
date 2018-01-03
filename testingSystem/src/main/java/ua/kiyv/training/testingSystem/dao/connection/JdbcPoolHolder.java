package ua.kiyv.training.testingSystem.dao.connection;

/**
 * Created by Tanya on 03.01.2018.
 */
public class JdbcPoolHolder {

    private final String DB_CONFIG_FILENAME = "webProject/webProject.config/DBconfig.properties";
    private final String DB_CONFIG_PARAM_URL = "database.url";
    private final String DB_CONFIG_PARAM_DB_NAME = "database.dbName";
    private final String DB_CONFIG_PARAM_USER_NAME = "database.userName";
    private final String DB_CONFIG_PARAM_USER_PASSWORD = "database.userPassword";
    private final String DB_CONFIG_PARAM_DRIVER = "database.driver";
    private final String DB_CONFIG_PARAM_MAX_CONNECTIONS = "database.maxConnections";
    private final String DB_CONFIG_PARAM_CONNECTION_PROPERITES="database.connectionProperties";
}
