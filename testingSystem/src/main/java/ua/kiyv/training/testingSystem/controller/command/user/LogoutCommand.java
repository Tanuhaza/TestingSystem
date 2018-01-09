package ua.kiyv.training.testingSystem.controller.command.user;


import ua.kiyv.training.testingSystem.controller.Command;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+PagesPath.LOGIN_PATH);
        return PagesPath.REDIRECTED;
    }
}
