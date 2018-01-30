package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.model.entity.Topic;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class represents ConstructingQuiz  service
 *
 * Created by Tanya on 23.01.2018.
 */
public interface ConstructingQuizService {

    void create (Quiz quiz);

    void create (Topic topic);

    Optional<Quiz> findById(int id);

    List<Topic> findAllTopics();

    void update(Quiz quiz);

    void delete(Quiz quiz);

    public void createQuizAndAssosiateWithQuestion(Quiz quiz, List<Question> questions);

    public List<Question> getQuestionsByQuizID(int id);

    public List<Quiz> getQuizzesByTopicId(int id);

    public Map<Question,List<Option>> getQuestionOptionsMapByQuizID(int id);

    public Map<Question, List<Option>> getQuestionOptionsMapByQuizIDWithLimitPerPage(int quizId, int startFrom, int quantity);

    public List<Question> getQuestionsByQuizIDWithLimitPerPage(int id,int startFrom, int quantity);

    public List<Option> getOptionsByQuestionID(int id);

    public List<Integer> getQuizzesIDByQuestionID( int id);

    public int countAllQuestionByQuizId(int id);
}
