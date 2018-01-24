package ua.kiyv.training.testingSystem.dao;

import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;

import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public interface QuestionDao extends GenericDao<Question> {
    public List<Question> getAssosiatedQuestionByTopicId(int id);
    public List<Option> getAssociatedOptionsByQuestionID(int id);
    public List<Integer> getAssociatedTestsIDByQuestionID( int id);
}
