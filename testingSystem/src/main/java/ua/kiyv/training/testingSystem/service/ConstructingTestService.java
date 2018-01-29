package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.Topic;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Tanya on 23.01.2018.
 */
public interface ConstructingTestService {

    void create (Test test);

    void create (Topic topic);

    Optional<Test> findById(int id);

    List<Topic> findAllTopics();

    void update(Test test);

    void delete(Test test);

    public void createTestAndAssosiateWithQuestion(Test test, List<Question> questions);

    public List<Question> getQuestionsByTestID(int id);

    public List<Test> getTestsByTopicId(int id);

    public Map<Question,List<Option>> getQuestionOptionsMapByTestID(int id);

    public Map<Question, List<Option>> getQuestionOptionsMapByTestIDWithLimitPerPage(int testId, int startFrom, int quantity);

    public List<Question> getQuestionsByTestIDWithLimitPerPage(int id,int startFrom, int quantity);

    public List<Option> getOptionsByQuestionID(int id);

    public List<Integer> getTestsIDByQuestionID( int id);

    public int countAllQuestionByTestId(int id);
}
