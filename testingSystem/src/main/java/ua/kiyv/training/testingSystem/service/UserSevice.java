package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.model.entity.User;

import java.util.Optional;

/**
 * Created by Tanya on 05.01.2018.
 */
public interface UserSevice extends GenericService<User> {
    public Optional<User> getUserByLoginPassword(String login, String password);
}
