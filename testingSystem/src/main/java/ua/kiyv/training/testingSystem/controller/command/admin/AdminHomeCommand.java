package ua.kiyv.training.testingSystem.controller.command.admin;

import ua.kiyv.training.testingSystem.controller.Command;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminHomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return PagesPath.HOME_PAGE;
    }
}
