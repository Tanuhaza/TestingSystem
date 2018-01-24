package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.dao.QuestionDao;
import ua.kiyv.training.testingSystem.dao.TestDao;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.Topic;
import ua.kiyv.training.testingSystem.service.ServiceException;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.ConstructingTestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Tanya on 23.01.2018.
 */
public class ConstructingTestServiceImpl implements ConstructingTestService {

    @Override
    public void create(Test test) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createTestDao().create(test);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException e) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            throw new ServiceException("Transaction failed.", e);
        }
    }

    @Override
    public void create(Topic topic) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createTopicDao().create(topic);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException e) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            throw new ServiceException("Transaction failed.", e);
        }
    }

    @Override
    public Optional<Test> findById(int id) {
        return  Optional.of(JdbcDaoFactory.getInstance().createTestDao().findById(id));}

    @Override
    public List<Topic> findAllTopics() {
        return  JdbcDaoFactory.getInstance().createTopicDao().findAll();}

    @Override
    public void update(Test test) {
        JdbcDaoFactory.getInstance().createTestDao().update(test);}

    @Override
    public void delete(Test test) {
        JdbcDaoFactory.getInstance().createTestDao().delete(test);}

    @Override
    public void createTestAndAssosiateWithQuestion(Test test, List<Question> questions) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createTestDao().create(test);
            TestDao testDao = JdbcDaoFactory.getInstance().createTestDao();
            questions.forEach(question -> testDao.associate(test,question));
            JdbcTransactionHelper.getInstance().commitTransaction();
        }catch (DaoException e) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            throw new ServiceException("Transaction failed.", e);
        }
    }

    @Override
    public List<Question> getQuestionsByTestID(int id) {
        QuestionDao questionDao = JdbcDaoFactory.getInstance().createQuestionDao();
        return JdbcDaoFactory.getInstance().createTestDao().getAssociatedQuestionsIDByTestID(id)
                .stream()
                .map(questionDao :: findById )
                .collect(Collectors.toList());}


    @Override
    public List<Option> getOptionsByQuestionID(int id) {
        return  JdbcDaoFactory.getInstance().createQuestionDao().getAssociatedOptionsByQuestionID(id);}

    @Override
    public List<Integer> getTestsIDByQuestionID(int id) {
        return   JdbcDaoFactory.getInstance().createQuestionDao().getAssociatedTestsIDByQuestionID(id);}

    @Override
    public List<Test> getTestsByTopicId(int id) {
        return JdbcDaoFactory.getInstance().createTestDao().getAssosiatedTestsByTopicId(id);}

    @Override
    public Map<Question,List<Option>> getQuestionOptionsMapByTestID(int testId) {

        Map<Question, List<Option>> test = new HashMap<>();

        ConstructingTestService constructingTestService = ServiceFactory.getInstance().createConstructingTestService();
        List<Question> questionsId = constructingTestService.getQuestionsByTestID(testId);
        questionsId.stream()
                .forEach(question ->
                        test.put(question, constructingTestService.getOptionsByQuestionID(question.getId())));

        return test;
    }
}
