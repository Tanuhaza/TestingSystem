package ua.kiyv.training.testingSystem.service.impl;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.DaoFactory;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.dao.QuestionDao;
import ua.kiyv.training.testingSystem.dao.QuizDao;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.model.entity.Topic;
import ua.kiyv.training.testingSystem.service.ServiceException;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.ConstructingQuizService;
import ua.kiyv.training.testingSystem.utils.constants.LoggerMessages;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation for ConstructingTest service
 */
public class ConstructingQuizServiceImpl implements ConstructingQuizService {

    private static final Logger logger = Logger.getLogger(ConstructingQuizServiceImpl.class);

    @Override
    public void create(Quiz quiz) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createQuizDao().create(quiz);
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
    public Optional<Quiz> findById(int id) {
        return Optional.of(JdbcDaoFactory.getInstance().createQuizDao().findById(id));
    }

    @Override
    public List<Topic> findAllTopics() {
        return JdbcDaoFactory.getInstance().createTopicDao().findAll();
    }

    @Override
    public void update(Quiz quiz) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createQuizDao().update(quiz);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public void delete(Quiz quiz) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createQuizDao().delete(quiz);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public void createQuizAndAssosiateWithQuestion(Quiz quiz, List<Question> questions) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            QuizDao quizDao = JdbcDaoFactory.getInstance().createQuizDao();
            quizDao.create(quiz);
            questions.forEach(question -> quizDao.associate(quiz, question));
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }
@Override
    public int countAllQuestionByQuizId(int id){
        return DaoFactory.getInstance().createQuizDao().countAllQuestionByQuizId(id);
}

    @Override
    public List<Question> getQuestionsByQuizID(int id) {
        QuestionDao questionDao = JdbcDaoFactory.getInstance().createQuestionDao();
        return JdbcDaoFactory.getInstance().createQuizDao().getAssociatedQuestionsIDByQuizID(id)
                .stream()
                .map(questionDao::findById)
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> getQuestionsByQuizIDWithLimitPerPage(int id, int startFrom, int quantity) {
        QuestionDao questionDao = JdbcDaoFactory.getInstance().createQuestionDao();
        return JdbcDaoFactory.getInstance().createQuizDao().getAssociatedQuestionsIDByQuizIDWithLimitPerPage(id, startFrom, quantity)
                .stream()
                .map(questionDao::findById)
                .collect(Collectors.toList());
    }

    @Override
    public List<Option> getOptionsByQuestionID(int id) {
        return JdbcDaoFactory.getInstance().createQuestionDao().getAssociatedOptionsByQuestionID(id);
    }

    @Override
    public List<Integer> getQuizzesIDByQuestionID(int id) {
        return JdbcDaoFactory.getInstance().createQuestionDao().getAssociatedQuizzesIDByQuestionID(id);
    }

    @Override
    public List<Quiz> getQuizzesByTopicId(int id) {
        return JdbcDaoFactory.getInstance().createQuizDao().getAssosiatedQuizzesByTopicId(id);
    }

    @Override
    public Map<Question, List<Option>> getQuestionOptionsMapByQuizID(int testId) {

        Map<Question, List<Option>> quiz = new HashMap<>();

        ConstructingQuizService constructingQuizService = ServiceFactory.getInstance().createConstructingQuizService();
        List<Question> questionsId = constructingQuizService.getQuestionsByQuizID(testId);
        questionsId.stream()
                .forEach(question ->
                        quiz.put(question, constructingQuizService.getOptionsByQuestionID(question.getId())));

        return quiz;
    }

    @Override
    public Map<Question, List<Option>> getQuestionOptionsMapByQuizIDWithLimitPerPage(int testId, int startFrom, int quantity) {

        Map<Question, List<Option>> quiz = new HashMap<>();

        ConstructingQuizService constructingQuizService = ServiceFactory.getInstance().createConstructingQuizService();
        List<Question> questionsId = constructingQuizService.getQuestionsByQuizIDWithLimitPerPage(testId, startFrom, quantity);
        questionsId.stream()
                .forEach(question ->
                        quiz.put(question, constructingQuizService.getOptionsByQuestionID(question.getId())));

        return quiz;
    }
}
