package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.model.entity.UserResponse;

import java.util.List;
import java.util.Map;

/**
 * This class represents UserResponse service
 *
 * Created by Tanya on 23.01.2018.
 */
public interface UserResponseService {

    /**
     * @param userResponse response of user to be created and saved in data base
     */
    void create(UserResponse userResponse);

    void update(UserResponse userResponse);

    public void delete(UserResponse userResponse);

    /**
     * @param userId      user id
     * @param testId      test id
     * @param passedTimes define first or last time test was passed, first=1,last=2
     */

    public void deleteByPassedTimes(int userId, int testId, int passedTimes);

    /**
     * @param userId      user id
     * @param passedTimes define first or last time test was passed, first=1,last=2
     * @return list of Quiz entity which was passed by user
     */

    public List<Quiz> getQuizzesPassedByUser(int userId, int passedTimes);

    /**
     * @param userId user id
     * @return list of Quiz entity which was passed by user firstly
     */

    public List<Quiz> getQuizzesPassedFirstly(int userId);

    /**
     * @param userId user id
     * @return map
     */

    public Map<Quiz, Integer> getQuizResultMapFirstlyPassed(int userId);

    /**
     * @param userId      user id
     * @param testId      test id
     * @param passedTimes define first or last time test was passed, first=1,last=2
     * @return test score which was passed by user
     */

    public int getTotalScoreByPassedTimes(int userId, int testId, int passedTimes);

    /**
     * @param resultMap resultmap consists of questions and options which were checked by user
     * @return test score which was passed by user
     */

    public int getTotalScore(Map<Question, List<Option>> resultMap);

    /**
     * @param userId user id
     * @param passedTimes define first or last time test was passed, first=1,last=2
     * @return map which consist of test's name and score for it
     */

    public Map<Quiz, Integer> getQuizResultMapByPassedTimes(int userId, int passedTimes);

    /**
     * @param userId      user id
     * @param testId test id
     * @return list of integer which can contains 0 or 1 or  {1,2},
     * 1 means test was passed first time, 2 means test was passed last
     */

    public List<Integer> getPassedTimes(int userId, int testId);



}
