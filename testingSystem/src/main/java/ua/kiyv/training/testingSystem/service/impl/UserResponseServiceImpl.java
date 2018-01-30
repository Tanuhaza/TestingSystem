package ua.kiyv.training.testingSystem.service.impl;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.DaoFactory;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.model.entity.UserResponse;
import ua.kiyv.training.testingSystem.service.ConstructingQuizService;
import ua.kiyv.training.testingSystem.service.ServiceException;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserResponseService;
import ua.kiyv.training.testingSystem.utils.constants.LoggerMessages;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation for UserResponse service
 */

public class UserResponseServiceImpl implements UserResponseService {

    private static final Logger logger = Logger.getLogger(UserResponseServiceImpl.class);

    @Override
    public void create(UserResponse userResponse) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserResponseDao().create(userResponse);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public void update(UserResponse userResponse) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserResponseDao().update(userResponse);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public void delete(UserResponse userResponse) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserResponseDao().delete(userResponse);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }

    @Override
    public List<Quiz> getQuizzesPassedByUser(int userId, int passedTimes) {
        List<Integer> passedQuizzesId = DaoFactory.getInstance().createUserResponseDao().getPassedTestsId(userId, passedTimes);
        ConstructingQuizService quizService = ServiceFactory.getInstance().createConstructingQuizService();
        return passedQuizzesId
                .stream()
                .map(quizId -> quizService.findById(quizId))
                .filter(quiz -> quiz.isPresent())
                .map(quiz -> quiz.get())
                .collect(Collectors.toList());
    }

    @Override
    public List<Quiz> getQuizzesPassedFirstly(int userId) {
        List<Integer> passedTestsId = DaoFactory.getInstance().createUserResponseDao().getPassedTestsId(userId);
        ConstructingQuizService testService = ServiceFactory.getInstance().createConstructingQuizService();
        return passedTestsId
                .stream()
                .filter(n -> !getPassedTimes(userId, n).contains(2))
                .map(testId -> testService.findById(testId))
                .filter(test -> test.isPresent())
                .map(test -> test.get())
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalScoreByPassedTimes(int userId, int testId, int passedTimes) {
        return DaoFactory.getInstance().createUserResponseDao().getTotalScoreByPassedTimes(userId, testId, passedTimes);
    }


    @Override
    public Map<Quiz, Integer> getQuizResultMapByPassedTimes(int userId, int passedTimes) {
        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
        List<Quiz> passedQuizzes = userResponseService.getQuizzesPassedByUser(userId, passedTimes);
        return passedQuizzes
                .stream()
                .collect(Collectors.toMap(Function.identity(),
                        test -> userResponseService.getTotalScoreByPassedTimes(userId, test.getId(), passedTimes)));
    }

    @Override
    public Map<Quiz, Integer> getQuizResultMapFirstlyPassed(int userId) {
        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
        List<Quiz> passedQuizs = userResponseService.getQuizzesPassedFirstly(userId);
        return passedQuizs
                .stream()
                .collect(Collectors.toMap(Function.identity(),
                        test -> userResponseService.getTotalScoreByPassedTimes(userId, test.getId(), 1)));
    }

    public int getTotalScore(Map<Question, List<Option>> resultMap) {
        return resultMap.values()
                .stream()
                .flatMap(options -> options.stream())
                .collect(Collectors.summingInt(Option::getScore));
    }

    @Override
    public List<Integer> getPassedTimes(int userId, int testId) {
        return DaoFactory.getInstance().createUserResponseDao()
                .getPassedTimes(userId, testId);
    }

    @Override
    public void deleteByPassedTimes(int userId, int testId, int passedTimes) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserResponseDao().deleteByPassedTimes(userId, testId, passedTimes);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException ex) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            logger.error(LoggerMessages.WRONG_TRANSACTION);
            throw new ServiceException(ex, MessageKeys.WRONG_TRANSACTION);
        }
    }
}

