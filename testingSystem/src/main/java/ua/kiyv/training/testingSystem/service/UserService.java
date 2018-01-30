package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * * This class represents user service
 * <p>
 * Created by Tanya on 23.01.2018.
 */
public interface UserService {

    /**
     * @param user entity of user to be created and saved in data base
     */
    void create(User user);

    /**
     * @param id user's id, whose entity will be returned
     * @return user entity with specified id
     */
    Optional<User> findById(int id);

    /**
     * @return list of user entity
     */
    List<User> findAll();

    /**
     * @param user entity of user to be updated and saved in data base
     */
    void update(User user);

    /**
     * @param user entity of user to be deleted from data base
     */
    void delete(User user);

    /**
     * @param login    user's login, whose entity will be returned
     * @param password user's password, whose entity will be returned
     * @return user entity with specified email (login)
     */
    public Optional<User> getUserByLoginPassword(String login, String password);

    /**
     * @return integer value which define number of users
     */
    public int countAllUsers();

    /**
     * this method is used for pagination
     *
     * @param startFrom specify position from which date will be pulled
     * @param quantity  number of users to be pulled
     * @return list of user entity
     */
    public List<User> getAllWithLimitPerPage(int startFrom, int quantity);

}
