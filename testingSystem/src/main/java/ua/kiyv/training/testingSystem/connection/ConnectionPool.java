package ua.kiyv.training.testingSystem.connection;


public interface ConnectionPool {

    DaoConnection getConnection();
}
