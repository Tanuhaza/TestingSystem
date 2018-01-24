package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.DaoFactory;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.dao.UserDao;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceException;
import ua.kiyv.training.testingSystem.service.UserService;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.util.List;
import java.util.Optional;

/**
 * Created by Tanya on 23.01.2018.
 */
public class UserServiceImpl implements UserService {

    @Override
    public void create(User user) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserDao().create(user);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException e) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            throw new ServiceException("Transaction failed.", e);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.of(JdbcDaoFactory.getInstance().createUserDao().findById(id));}

    @Override
    public List<User> findAll() {
        return JdbcDaoFactory.getInstance().createUserDao().findAll();}

    @Override
    public void update(User user) {
        JdbcDaoFactory.getInstance().createUserDao().update(user);}

    @Override
    public void delete(User user) {
        JdbcDaoFactory.getInstance().createUserDao().delete(user);}

    @Override
    public Optional<User> getUserByLoginPassword(String login, String password) {
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        User user=userDao.findByLogin(login);
        if (!password.equals(user.getPassword())){
            throw  new ServiceException(MessageKeys.WRONG_LOGIN_DATA);
        }
        return Optional.of(user);
    }


}
