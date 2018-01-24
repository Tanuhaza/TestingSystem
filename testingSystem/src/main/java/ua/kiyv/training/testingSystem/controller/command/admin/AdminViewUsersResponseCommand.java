package ua.kiyv.training.testingSystem.controller.command.admin;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.utils.ParamExtractor;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Tanya on 18.01.2018.
 */
public class AdminViewUsersResponseCommand extends CommandWrapper{

    public AdminViewUsersResponseCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    ParamExtractor paramExtractor = new ParamExtractor();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = paramExtractor.extractSingleIntPathParam(request);
        Map<Test,Integer> testResultMap =  ServiceFactory.getInstance().createUserResponseService().getTestResultMapByPassedTimes(userId,1);
        request.setAttribute(Attributes.TEST_RESULT_MAP, testResultMap);

        return PagesPath.ADMIN_USER_RESPONSE_PAGE;}
}
