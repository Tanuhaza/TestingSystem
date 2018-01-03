package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.dao.QuestionDao;
import ua.kiyv.training.testingSystem.model.Question;

import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcQuestionDao implements QuestionDao {
    @Override
    public void create(Question entity) {

    }

    @Override
    public Question findById(int id) {
        return null;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public void update(Question entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() throws Exception {

    }
}
