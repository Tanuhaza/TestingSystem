package ua.kiyv.training.testingSystem.controller.command.admin;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tanya on 18.01.2018.
 */
public class AdminViewUsersCommand extends CommandWrapper{

    public AdminViewUsersCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = ServiceFactory.getInstance().createUserService().findAll();
        request.setAttribute(Attributes.USERS, users);
        return PagesPath.ADMIN_VIEW_USERS_PAGE;
    }
}
