package ua.kiyv.training.testingSystem.service.impl;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.DaoFactory;
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
import ua.kiyv.training.testingSystem.utils.constants.LoggerMessages;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation for ConstructingTest service
 */
public class ConstructingTestServiceImpl implements ConstructingTestService {

    private static final Logger logger = Logger.getLogger(ConstructingTestServiceImpl.class);

    @Override
    public void create(Test test) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createTestDao().create(test);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public void create(Topic topic) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createTopicDao().create(topic);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public Optional<Test> findById(int id) {
        return Optional.of(JdbcDaoFactory.getInstance().createTestDao().findById(id));
    }

    @Override
    public List<Topic> findAllTopics() {
        return JdbcDaoFactory.getInstance().createTopicDao().findAll();
    }

    @Override
    public void update(Test test) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createTestDao().update(test);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public void delete(Test test) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createTestDao().delete(test);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public void createTestAndAssosiateWithQuestion(Test test, List<Question> questions) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            TestDao testDao = JdbcDaoFactory.getInstance().createTestDao();
            testDao.create(test);
            questions.forEach(question -> testDao.associate(test, question));
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }
@Override
    public int countAllQuestionByTestId(int id){
        return DaoFactory.getInstance().createTestDao().countAllQuestionByTestId(id);
}

    @Override
    public List<Question> getQuestionsByTestID(int id) {
        QuestionDao questionDao = JdbcDaoFactory.getInstance().createQuestionDao();
        return JdbcDaoFactory.getInstance().createTestDao().getAssociatedQuestionsIDByTestID(id)
                .stream()
                .map(questionDao::findById)
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> getQuestionsByTestIDWithLimitPerPage(int id, int startFrom, int quantity) {
        QuestionDao questionDao = JdbcDaoFactory.getInstance().createQuestionDao();
        return JdbcDaoFactory.getInstance().createTestDao().getAssociatedQuestionsIDByTestIDWithLimitPerPage(id, startFrom, quantity)
                .stream()
                .map(questionDao::findById)
                .collect(Collectors.toList());
    }

    @Override
    public List<Option> getOptionsByQuestionID(int id) {
        return JdbcDaoFactory.getInstance().createQuestionDao().getAssociatedOptionsByQuestionID(id);
    }

    @Override
    public List<Integer> getTestsIDByQuestionID(int id) {
        return JdbcDaoFactory.getInstance().createQuestionDao().getAssociatedTestsIDByQuestionID(id);
    }

    @Override
    public List<Test> getTestsByTopicId(int id) {
        return JdbcDaoFactory.getInstance().createTestDao().getAssosiatedTestsByTopicId(id);
    }

    @Override
    public Map<Question, List<Option>> getQuestionOptionsMapByTestID(int testId) {

        Map<Question, List<Option>> test = new HashMap<>();

        ConstructingTestService constructingTestService = ServiceFactory.getInstance().createConstructingTestService();
        List<Question> questionsId = constructingTestService.getQuestionsByTestID(testId);
        questionsId.stream()
                .forEach(question ->
                        test.put(question, constructingTestService.getOptionsByQuestionID(question.getId())));

        return test;
    }

    @Override
    public Map<Question, List<Option>> getQuestionOptionsMapByTestIDWithLimitPerPage(int testId, int startFrom, int quantity) {

        Map<Question, List<Option>> test = new HashMap<>();

        ConstructingTestService constructingTestService = ServiceFactory.getInstance().createConstructingTestService();
        List<Question> questionsId = constructingTestService.getQuestionsByTestIDWithLimitPerPage(testId, startFrom, quantity);
        questionsId.stream()
                .forEach(question ->
                        test.put(question, constructingTestService.getOptionsByQuestionID(question.getId())));

        return test;
    }
}
