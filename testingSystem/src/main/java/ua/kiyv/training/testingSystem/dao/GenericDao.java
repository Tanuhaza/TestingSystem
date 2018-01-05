package ua.kiyv.training.testingSystem.dao;

import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public interface GenericDao<T> {
    void create (T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}
