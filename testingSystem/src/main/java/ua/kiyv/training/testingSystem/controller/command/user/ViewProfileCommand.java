package ua.kiyv.training.testingSystem.controller.command.user;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserResponseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static ua.kiyv.training.testingSystem.utils.constants.Attributes.*;
import static ua.kiyv.training.testingSystem.utils.constants.PagesPath.LOGIN_PAGE;
import static ua.kiyv.training.testingSystem.utils.constants.PagesPath.PROFILE_PAGE;

/**
 * Created by Tanya on 21.01.2018.
 */
public class ViewProfileCommand extends CommandWrapper{
    public ViewProfileCommand() {super(LOGIN_PAGE);}

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = (int)request.getSession().getAttribute(USER_ID);
        Optional<User>  user = ServiceFactory.getInstance().createUserService().findById(userId);
        if (user.isPresent()){
            User person = user.get();
            request.setAttribute(USER, person);
        }

        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
        Map<Quiz,Integer> quizResultMapByFirstlyPassed =  userResponseService.getQuizResultMapFirstlyPassed(userId);
        Map<Quiz,Integer> quizResultMapLastTimePassed =  userResponseService.getQuizResultMapByPassedTimes(userId,2);

        request.setAttribute(QUIZ_RESULT_MAP_FIRST_TIME, quizResultMapByFirstlyPassed);
        request.setAttribute(QUIZ_RESULT_MAP_LAST_TIME, quizResultMapLastTimePassed);
        return PROFILE_PAGE;
    }
}
