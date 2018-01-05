package ua.kiyv.training.testingSystem.service;

import java.util.List;

/**
 * Created by Tanya on 05.01.2018.
 */
public interface GenericService<T> {
    void create (T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}
