package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.dao.OptionDao;
import ua.kiyv.training.testingSystem.model.Option;

import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcOptionDao implements OptionDao {
    @Override
    public void create(Option entity) {

    }

    @Override
    public Option findById(int id) {
        return null;
    }

    @Override
    public List<Option> findAll() {
        return null;
    }

    @Override
    public void update(Option entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() throws Exception {

    }
}
