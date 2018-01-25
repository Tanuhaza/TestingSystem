package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.DaoFactory;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.dao.OptionDao;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.UserResponse;
import ua.kiyv.training.testingSystem.service.ConstructingTestService;
import ua.kiyv.training.testingSystem.service.ServiceException;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserResponseService;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Tanya on 23.01.2018.
 */
public class UserResponseServiceImpl implements UserResponseService {

    @Override
    public void create(UserResponse userResponse) {

        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserResponseDao().create(userResponse);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException e) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            throw new ServiceException("Transaction failed.", e);
        }
    }

    @Override
    public void update(UserResponse userResponse) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserResponseDao().update(userResponse);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException e) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            throw new ServiceException("Transaction failed.", e);
        }
    }

    @Override
    public void delete(UserResponse userResponse) {
        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserResponseDao().delete(userResponse);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException e) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            throw new ServiceException("Transaction failed.", e);
        }
    }

    @Override
    public List<Test> getTestsPassedByUser(int userId, int passedTimes) {
        List<Integer> passedTestsId= DaoFactory.getInstance().createUserResponseDao().getPassedTestsId(userId,passedTimes);
        ConstructingTestService testService = ServiceFactory.getInstance().createConstructingTestService();
        return passedTestsId
                .stream()
                .map(testId -> testService.findById(testId))
                .filter(test->test.isPresent())
                .map(test -> test.get())
                .collect(Collectors.toList());
    }
    @Override
    public int getTotalScoreByPassedTimes(int userId,int testId,int passedTimes) {

        List<UserResponse> userResponses = DaoFactory.getInstance().createUserResponseDao().getUserResponseByUserAndTestId(userId,testId);
        return   userResponses
                .stream()
                .filter(userResponse -> userResponse.getPassedTimes()==passedTimes)
                .map(userResponse -> userResponse.getTotalScore())
                .findFirst().orElseThrow(()->new ServiceException(MessageKeys.USER_NOT_FOUND));
    }

    @Override
    public Map<Test,Integer> getTestResultMapByPassedTimes (int userId,int passedTimes){

        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
        List<Test> passedTests = userResponseService.getTestsPassedByUser(userId,passedTimes);
        return  passedTests
                .stream()
                .collect(Collectors.toMap(Function.identity(),
                        test -> userResponseService.getTotalScoreByPassedTimes(userId,test.getId(),passedTimes)));
    }


    public int getTotalScore(Map<Question, List<Option>> resultMap){
        return resultMap.values()
                .stream()
                .flatMap(options -> options.stream())
                .collect(Collectors.summingInt(Option::getScore));
    }

    @Override
    public List<Integer> getPassedTimes(int userId, int testId) {
        return DaoFactory.getInstance().createUserResponseDao()
               .getPassedTimes(userId,testId);

    }

    @Override
    public void deleteByPassedTimes(int userId, int testId, int passedTimes){

        JdbcTransactionHelper.getInstance().beginTransaction();
        try {
            JdbcDaoFactory.getInstance().createUserResponseDao().deleteByPassedTimes(userId,testId,passedTimes);
            JdbcTransactionHelper.getInstance().commitTransaction();
        } catch (DaoException e) {
            JdbcTransactionHelper.getInstance().rollbackTransaction();
            throw new ServiceException("Transaction failed.", e);
        }
    }
}

