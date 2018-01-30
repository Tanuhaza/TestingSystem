package ua.kiyv.training.testingSystem.dao;

import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Quiz;

import java.util.List;

/**
 * Created by Tanya on 14.01.2018.
 */
public interface QuizDao extends GenericDao<Quiz> {
    public List<Integer> getAssociatedQuestionsIDByQuizID(int id);
    public List<Quiz> getAssosiatedQuizzesByTopicId(int id);
    public boolean associate(Quiz quiz, Question question);
    public List<Integer> getAssociatedQuestionsIDByQuizIDWithLimitPerPage(int id,int startFrom, int quantity);
    public int countAllQuestionByQuizId(int id);
}
