package ua.kiyv.training.testingSystem.controller.command.admin;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.utils.ParamExtractor;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Tanya on 18.01.2018.
 */
public class AdminViewUsersResponseCommand extends CommandWrapper {

    public AdminViewUsersResponseCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    ParamExtractor paramExtractor = new ParamExtractor();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = paramExtractor.extractSingleIntPathParam(request);
        Optional<User> user = ServiceFactory.getInstance().createUserService().findById(userId);
        if (user.isPresent()) {
            User person = user.get();
            request.setAttribute(Attributes.USER, person);
        }
        Map<Quiz, Integer> quizResultMapFirstTimePassed = ServiceFactory.getInstance().createUserResponseService()
                .getQuizResultMapByPassedTimes(userId, 1);
        Map<Quiz, Integer> quizResultMapLastTimePassed = ServiceFactory.getInstance().createUserResponseService()
                .getQuizResultMapByPassedTimes(userId, 2);
        request.setAttribute(Attributes.QUIZ_RESULT_MAP_FIRST_TIME, quizResultMapFirstTimePassed);
        request.setAttribute(Attributes.QUIZ_RESULT_MAP_LAST_TIME, quizResultMapLastTimePassed);
        return PagesPath.ADMIN_USER_RESPONSE_PAGE;
    }
}
