package ua.kiyv.training.testingSystem.dao;

import ua.kiyv.training.testingSystem.model.entity.User;

import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public interface UserDao extends GenericDao<User> {
    public User findByLogin(String login);
    public List<User> findByRole(User.Role role);

}
