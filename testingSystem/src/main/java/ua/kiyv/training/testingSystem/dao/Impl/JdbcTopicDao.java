package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.dao.TopicDao;
import ua.kiyv.training.testingSystem.model.Topic;

import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcTopicDao implements TopicDao {
    @Override
    public void create(Topic entity) {

    }

    @Override
    public Topic findById(int id) {
        return null;
    }

    @Override
    public List<Topic> findAll() {
        return null;
    }

    @Override
    public void update(Topic entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() throws Exception {

    }
}
