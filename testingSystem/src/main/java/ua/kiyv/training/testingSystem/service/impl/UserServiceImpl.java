package ua.kiyv.training.testingSystem.service.impl;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.DaoFactory;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.dao.UserDao;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceException;
import ua.kiyv.training.testingSystem.service.UserService;
import ua.kiyv.training.testingSystem.utils.constants.LoggerMessages;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation for User service
 */

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public void create(User user) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserDao().create(user);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.of(JdbcDaoFactory.getInstance().createUserDao().findById(id));
    }

    @Override
    public List<User> findAll() {
        return JdbcDaoFactory.getInstance().createUserDao().findAll();
    }

    @Override
    public void update(User user) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserDao().update(user);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public void delete(User user) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserDao().delete(user);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public Optional<User> getUserByLoginPassword(String login, String password) {
        UserDao userDao = JdbcDaoFactory.getInstance().createUserDao();
        User user =
                userDao.findByLogin(login);
        if (!password.equals(user.getPassword())) {
            throw new ServiceException(MessageKeys.WRONG_LOGIN_DATA);
        }
        return Optional.of(user);
    }

    @Override
    public int countAllUsers() {
        return DaoFactory.getInstance().createUserDao().countAllUsers();
    }

    @Override
    public List<User> getAllWithLimitPerPage(int startFrom, int quantity) {
        return DaoFactory.getInstance().createUserDao().getAllWithLimitPerPage(startFrom, quantity);
    }

}
