package ua.kiyv.training.testingSystem.service.impl;

import javafx.concurrent.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoFactory;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.dao.QuizDao;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Tanya on 29.01.2018.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({JdbcDaoFactory.class, JdbcTransactionHelper.class})
public class ConstructingQuizService {

    @Mock
    private DaoFactory factory;

    @Mock
    private JdbcTransactionHelper transactionHelper;

    @Mock
    private QuizDao quizDao;

    @Before
    public void setUpMocks() {
        mockStatic(DaoFactory.class);
        mockStatic(JdbcTransactionHelper.class);
        when(DaoFactory.getInstance()).thenReturn(factory);
        when(JdbcTransactionHelper.getInstance()).thenReturn(transactionHelper);
        when(factory.createQuizDao()).thenReturn(quizDao);
    }

    @Test
    public void testTransactionSuccessForCreateNewAndAssociateWithQuestions() {
        Quiz quiz = new Quiz();
        Question[] questions = new Question[5];
        Arrays.fill(questions, new Question());
        InOrder inOrder = inOrder(transactionHelper,quizDao);

        ServiceFactory.getInstance().createConstructingQuizService()
                .createQuizAndAssosiateWithQuestion(quiz, Arrays.asList(questions));

        inOrder.verify(transactionHelper).beginTransaction();
        inOrder.verify(quizDao).create(eq(quiz));
        inOrder.verify(quizDao, times(5)).associate(eq(quiz), any(Question.class));
        inOrder.verify(transactionHelper).commitTransaction();
        inOrder.verifyNoMoreInteractions();
    }
}
