package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.model.entity.Topic;
import ua.kiyv.training.testingSystem.service.TopicService;

import java.util.List;

/**
 * Created by Tanya on 05.01.2018.
 */
public class TopicServiceImpl implements TopicService {
    @Override
    public void create(Topic topic) {
        JdbcDaoFactory.getInstance().createTopicDao().create(topic);}

    @Override
    public Topic findById(int id) {
        return JdbcDaoFactory.getInstance().createTopicDao().findById(id);}

    @Override
    public List<Topic> findAll() {
        return JdbcDaoFactory.getInstance().createTopicDao().findAll();}


    @Override
    public void update(Topic topic) {
        JdbcDaoFactory.getInstance().createTopicDao().update(topic);}

    @Override
    public void delete(Topic topic) {
        JdbcDaoFactory.getInstance().createTopicDao().delete(topic);}
    }
