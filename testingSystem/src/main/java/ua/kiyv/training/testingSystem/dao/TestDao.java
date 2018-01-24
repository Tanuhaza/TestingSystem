package ua.kiyv.training.testingSystem.dao;

import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Test;

import java.util.List;

/**
 * Created by Tanya on 14.01.2018.
 */
public interface TestDao extends GenericDao<Test> {
    public List<Integer> getAssociatedQuestionsIDByTestID(int id);
    public List<Test> getAssosiatedTestsByTopicId(int id);
    public boolean associate(Test test,Question question);
}
