package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.model.entity.Question;

import java.util.List;

/**
 * Created by Tanya on 05.01.2018.
 */
public interface QuestionService extends GenericService<Question> {
    public List<Question> chooseRundomlyQuestionsByQuantity(int quantity, List<Question> questions);
}
