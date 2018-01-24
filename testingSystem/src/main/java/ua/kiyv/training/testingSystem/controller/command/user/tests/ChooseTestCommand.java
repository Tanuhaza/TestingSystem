package ua.kiyv.training.testingSystem.controller.command.user.tests;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.utils.ParamExtractor;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Tanya on 12.01.2018.
 */
public class ChooseTestCommand extends CommandWrapper {
    public ChooseTestCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    ParamExtractor paramExtractor = new ParamExtractor();

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int testId = paramExtractor.extractSingleIntPathParam(request);
        request.getSession().setAttribute(Attributes.TEST_ID,testId);

        Map<Question, List<Option>> test = ServiceFactory.getInstance()
                .createConstructingTestService().getQuestionOptionsMapByTestID(testId);
        request.setAttribute(Attributes.TEST, test);

        return PagesPath.TEST_PAGE;
    }
}
