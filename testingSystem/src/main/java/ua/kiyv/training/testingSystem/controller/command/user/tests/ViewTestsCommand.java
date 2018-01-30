package ua.kiyv.training.testingSystem.controller.command.user.tests;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.utils.ParamExtractor;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tanya on 15.01.2018.
 */
public class ViewTestsCommand extends CommandWrapper {

    public ViewTestsCommand() {
        super(PagesPath.LOGIN_PAGE);
    }


    ParamExtractor paramExtractor = new ParamExtractor();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int topicId = paramExtractor.extractSingleIntPathParam(request);
        request.getSession().setAttribute(Attributes.TOPIC_ID,topicId);
        List<Quiz> quizzes = ServiceFactory.getInstance().createConstructingQuizService().getQuizzesByTopicId(topicId);
        request.setAttribute(Attributes.QUIZZES, quizzes);
        return PagesPath.QUIZ_VIEW_PAGE;
    }
}
