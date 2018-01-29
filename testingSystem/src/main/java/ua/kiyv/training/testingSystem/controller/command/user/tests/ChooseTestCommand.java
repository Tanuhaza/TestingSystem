package ua.kiyv.training.testingSystem.controller.command.user.tests;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.controller.ControllerException;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ConstructingTestService;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserService;
import ua.kiyv.training.testingSystem.service.impl.ConstructingTestServiceImpl;
import ua.kiyv.training.testingSystem.utils.ParamExtractor;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;
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
    private static final int itemsPerPage = 3;
    private static final int FIRST = 1;

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int testId = paramExtractor.extractSingleIntPathParam(request);
        boolean isQuestionChecked = true;
        request.getSession().setAttribute(Attributes.TEST_ID, testId);
//        placeNecessaryDataToRequest(request,testId);
        ConstructingTestService constructingTestService = ServiceFactory.getInstance()
                .createConstructingTestService();
        Map<Question, List<Option>> test = constructingTestService
                .getQuestionOptionsMapByTestID(testId);
        request.setAttribute(IS_QUESTION_CHECKED, isQuestionChecked);
        request.setAttribute(Attributes.TEST, test);
        return PagesPath.TEST_PAGE;
    }

    protected void placeNecessaryDataToRequest(HttpServletRequest request, int testId) {

        int currentPageNumber = getPageNumberFromRequest(request);
        int ordersStartFrom = calculateItemOffset(currentPageNumber);
        ConstructingTestService constructingTestService = ServiceFactory.getInstance()
                .createConstructingTestService();
        Map<Question, List<Option>> test = constructingTestService
                .getQuestionOptionsMapByTestIDWithLimitPerPage(testId, ordersStartFrom, itemsPerPage);
        int lastPageNumber = calculateLastPageNumber(constructingTestService.countAllQuestionByTestId(testId));

        while (currentPageNumber > lastPageNumber) {
            currentPageNumber = lastPageNumber;
            ordersStartFrom = calculateItemOffset(currentPageNumber);
            test = constructingTestService
                    .getQuestionOptionsMapByTestIDWithLimitPerPage(testId, ordersStartFrom, itemsPerPage);
            lastPageNumber = calculateLastPageNumber(constructingTestService.countAllQuestionByTestId(testId));
        }
        request.setAttribute(Attributes.TEST, test);
        request.setAttribute(CURRENT_PAGE, currentPageNumber);
        request.setAttribute(LAST_PAGE, lastPageNumber);
    }

    private int calculateItemOffset(int pageNumber) {
        return (pageNumber - FIRST) * itemsPerPage;
    }

    private int calculateLastPageNumber(int totalCount) {
        int lastPageNumber = (int) Math.ceil(1.0 * totalCount / itemsPerPage);
        return (lastPageNumber == 0) ? FIRST : lastPageNumber;
    }

    private int getPageNumberFromRequest(HttpServletRequest request) {
        if (request.getParameter(PAGE) == null) {
            return FIRST;
        }
        int requestedPageNumber = paramExtractor.extractIntFromString(request.getParameter(PAGE));
        if (requestedPageNumber < FIRST) {
            requestedPageNumber = FIRST;
        }
        return requestedPageNumber;
    }
}

