package ua.kiyv.training.testingSystem.controller.command.user;

import ua.kiyv.training.testingSystem.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kiyv.training.testingSystem.utils.constants.Attributes.PAGE_TITLE;
import static ua.kiyv.training.testingSystem.utils.constants.MessageKeys.TITLE_HOME;
import static ua.kiyv.training.testingSystem.utils.constants.PagesPath.HOME_PAGE;

/**
 * Created by Tanya on 06.01.2018.
 */
public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute(PAGE_TITLE, TITLE_HOME);
        return HOME_PAGE;

    }
}
