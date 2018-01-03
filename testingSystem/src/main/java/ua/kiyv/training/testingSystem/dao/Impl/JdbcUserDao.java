package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.dao.UserDao;
import ua.kiyv.training.testingSystem.model.User;

import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcUserDao implements UserDao {
    @Override
    public void create(User entity) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() throws Exception {

    }
}
