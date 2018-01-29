package ua.kiyv.training.testingSystem.controller.command.admin;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserService;
import ua.kiyv.training.testingSystem.utils.ParamExtractor;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ua.kiyv.training.testingSystem.utils.constants.Attributes.CURRENT_PAGE;
import static ua.kiyv.training.testingSystem.utils.constants.Attributes.LAST_PAGE;
import static ua.kiyv.training.testingSystem.utils.constants.Attributes.PAGE;

/**
 * Created by Tanya on 18.01.2018.
 */
public class AdminViewUsersCommand extends CommandWrapper {

    ParamExtractor paramExtractor = new ParamExtractor();
    private static final int itemsPerPage = 10;
    private static final int FIRST = 1;

    public AdminViewUsersCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        placeNecessaryDataToRequest(request);
        return PagesPath.ADMIN_VIEW_USERS_PAGE;
    }

    protected void placeNecessaryDataToRequest(HttpServletRequest request) {

        int currentPageNumber = getPageNumberFromRequest(request);
        int ordersStartFrom = calculateItemOffset(currentPageNumber);
        List<User> users = new ArrayList<>();

        UserService userService = ServiceFactory.getInstance().createUserService();
        users = userService.getAllWithLimitPerPage(ordersStartFrom, itemsPerPage);
        int lastPageNumber = calculateLastPageNumber(userService.countAllUsers());
        while (currentPageNumber > lastPageNumber) {
            currentPageNumber = lastPageNumber;
            ordersStartFrom = calculateItemOffset(currentPageNumber);
            users = userService.getAllWithLimitPerPage(ordersStartFrom, itemsPerPage);
            lastPageNumber = calculateLastPageNumber(userService.countAllUsers());
        }
        request.setAttribute(Attributes.USERS, users);
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
