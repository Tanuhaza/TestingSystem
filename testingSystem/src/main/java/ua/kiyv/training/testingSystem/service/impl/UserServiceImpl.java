package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.UserSevice;

import java.util.List;
import java.util.Optional;

/**
 * Created by Tanya on 05.01.2018.
 */
public class UserServiceImpl implements UserSevice {
    @Override
    public void create(User user) {
        JdbcDaoFactory.getInstance().createUserDao().create(user);}

    @Override
    public User findById(int id) {
        return JdbcDaoFactory.getInstance().createUserDao().findById(id);}

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
        return null;
    }
}
