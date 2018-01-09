package ua.kiyv.training.testingSystem.dao;

import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Topic;

import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public interface TopicDao extends GenericDao<Topic> {
    public List<Question> getAssosiatedQuestions(int id);
}
