package ua.kiyv.training.testingSystem.dao;

import ua.kiyv.training.testingSystem.model.entity.UserResponse;

import java.util.List;

/**
 * Created by Tanya on 14.01.2018.
 */
public interface UserResponseDao extends GenericDao<UserResponse> {
    public List<Integer> getPassedTestsIdByUserId(int userId);
    public List<UserResponse> getUserResponseByUserAndTestId(int userId,int testId);
    public List<Integer> getPassedTimes(int userId, int testId);
    public List<UserResponse> findByUserId(int id);
    public void deleteByPassedTimes(int userId, int testId, int passedTimes);
}
