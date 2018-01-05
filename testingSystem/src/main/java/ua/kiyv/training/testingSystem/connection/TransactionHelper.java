package ua.kiyv.training.testingSystem.connection;

public interface TransactionHelper {

    void beginTransaction();

    /** closes used connection */
    void rollbackTransaction();

    /** closes used connection */
    void commitTransaction();

    /**
     * If transaction has been started returns
     * used connection, else new connection.
     *  */
    DaoConnection getConnection();
}