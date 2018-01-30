package ua.kiyv.training.testingSystem.controller.command.user.tests;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.service.ConstructingQuizService;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.utils.ParamExtractor;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static ua.kiyv.training.testingSystem.utils.constants.Attributes.*;
import static ua.kiyv.training.testingSystem.utils.constants.Attributes.PAGE;


/**
 * Created by Tanya on 12.01.2018.
 */
public class ChooseTestCommand extends CommandWrapper {
    public ChooseTestCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    private static final Logger logger = Logger.getLogger(ChooseTestCommand.class);
    ParamExtractor paramExtractor = new ParamExtractor();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int quizId = paramExtractor.extractSingleIntPathParam(request);
        boolean isQuestionChecked = true;
        request.getSession().setAttribute(Attributes.QUIZ_ID, quizId);
        ConstructingQuizService constructingTestService = ServiceFactory.getInstance()
                .createConstructingQuizService();
        Map<Question, List<Option>> quiz = constructingTestService
                .getQuestionOptionsMapByQuizID(quizId);
        request.setAttribute(IS_QUESTION_CHECKED, isQuestionChecked);
        request.setAttribute(Attributes.QUIZ, quiz);
        return PagesPath.QUIZ_PAGE;
    }
}

