package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.service.QuestionService;

import java.util.List;

/**
 * Created by Tanya on 05.01.2018.
 */
public class QuestionServiceImpl implements QuestionService {
    @Override
    public void create(Question question) {
        JdbcDaoFactory.getInstance().createQuestionDao().create(question);}

    @Override
    public Question findById(int id) {
        return  JdbcDaoFactory.getInstance().createQuestionDao().findById(id);}

    @Override
    public List<Question> findAll() {
        return  JdbcDaoFactory.getInstance().createQuestionDao().findAll();}

    @Override
    public void update(Question question) {
        JdbcDaoFactory.getInstance().createQuestionDao().update(question);}

    @Override
    public void delete(Question question) {
        JdbcDaoFactory.getInstance().createQuestionDao().delete(question);}

    @Override
    public List<Question> chooseRundomlyQuestionsByQuantity(int quantity,List<Question> questions) {
        return null;
    }
}

