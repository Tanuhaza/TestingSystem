package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.UserResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by Tanya on 23.01.2018.
 */
public interface UserResponseService {

    void create (UserResponse userResponse);

    void update (UserResponse userResponse);

    public void delete(UserResponse userResponse);

    public List<Test> getTestsPassedByUser(int userId);

    public int getTotalScoreByPassedTimes(int userId,int testId, int passedTimes);

    public Map<Test,Integer> getTestResultMapByPassedTimes (int userId,int passedTimes);

    public int getTotalScore(Map<Question, List<Option>> resultMap);

    public List<Integer> getPassedTimes(int userId,int testId);

    public void deleteByPassedTimes(int userId, int testId, int passedTimes);

}
