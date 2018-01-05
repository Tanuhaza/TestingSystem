package ua.kiyv.training.testingSystem.connection.Jdbc;


import ua.kiyv.training.testingSystem.connection.ConnectionPool;
import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.TransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;

public class JdbcTransactionHelper implements TransactionHelper {

    private static JdbcTransactionHelper instance = new JdbcTransactionHelper();

    private ConnectionPool pool = JdbcConnectionPool.getInstance();
    private ThreadLocal<DaoConnection> local = new ThreadLocal<>();

    private JdbcTransactionHelper() {}

    public static JdbcTransactionHelper getInstance() {
        return instance;
    }

    @Override
    public void beginTransaction() {
        DaoConnection connection = pool.getConnection();
        connection.setIsInTransaction(true);
        local.set(connection);
    }

    @Override
    public void commitTransaction() {
        DaoConnection connection = local.get();
        if (connection == null) {
            throw new DaoException("Can't commit transaction: it has not been begun");
        }
        connection.commit();
        endTransaction(connection);
    }

    @Override
    public void rollbackTransaction() {
        DaoConnection connection = local.get();
        if (connection == null) {
            throw new DaoException("Can't rollback transaction: it has not been begun");
        }
        connection.rollback();
        endTransaction(connection);
    }

    public DaoConnection getConnection() {
        DaoConnection connection = local.get();
        if (connection == null) {
            connection = pool.getConnection();
        }
        return connection;
    }

    private void endTransaction(DaoConnection connection) {
        connection.setIsInTransaction(false);
        connection.close();
        local.set(null);
    }
}