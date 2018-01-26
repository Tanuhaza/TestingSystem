package ua.kiyv.training.testingSystem.controller.command.user;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserResponseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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

//    ParamExtractor paramExtractor = new ParamExtractor();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = (int)request.getSession().getAttribute(USER_ID);
        Optional<User>  user = ServiceFactory.getInstance().createUserService().findById(userId);
        if (user.isPresent()){
            User person = user.get();
            request.setAttribute(USER, person);
        }

        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
        Map<Test,Integer> testResultMapByFirstlyPassed =  userResponseService.getTestResultMapByFirstlyPassed(userId);
        Map<Test,Integer> testResultMapLastTimePassed =  userResponseService.getTestResultMapByPassedTimes(userId,2);

        request.setAttribute(FIRST_TEST_RESULT_MAP, testResultMapByFirstlyPassed);
        request.setAttribute(LAST_TEST_RESULT_MAP, testResultMapLastTimePassed);
        return PROFILE_PAGE;
    }
}
