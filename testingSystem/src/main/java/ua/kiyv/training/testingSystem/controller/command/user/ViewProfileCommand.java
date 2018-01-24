package ua.kiyv.training.testingSystem.controller.command.user;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static ua.kiyv.training.testingSystem.utils.constants.Attributes.TEST_RESULT_MAP;
import static ua.kiyv.training.testingSystem.utils.constants.Attributes.USER;
import static ua.kiyv.training.testingSystem.utils.constants.Attributes.USER_ID;
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
        Map<Test,Integer> testResultMap =  ServiceFactory.getInstance().createUserResponseService().getTestResultMapByPassedTimes(userId,1);
        request.setAttribute(TEST_RESULT_MAP, testResultMap);
        return PROFILE_PAGE;
    }
}
