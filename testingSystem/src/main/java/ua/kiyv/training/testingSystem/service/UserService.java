package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Tanya on 23.01.2018.
 */
public interface UserService {
    void create (User user);
    Optional<User> findById(int id);
    List<User> findAll();
    void update(User user);
    void delete(User user);
    public Optional<User> getUserByLoginPassword(String login, String password);
    public int countAllUsers();
    public List<User> getAllWithLimitPerPage(int startFrom, int quantity);

}
