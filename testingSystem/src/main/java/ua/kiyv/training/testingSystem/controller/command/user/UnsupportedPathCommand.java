package ua.kiyv.training.testingSystem.controller.command.user;


import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.kiyv.training.testingSystem.utils.constants.LoggerMessages.REQUESTED_UNSUPPORTED_URI;
import static ua.kiyv.training.testingSystem.utils.constants.PagesPath.HOME_PATH;
import static ua.kiyv.training.testingSystem.utils.constants.PagesPath.REDIRECTED;

/**
 * This class represents behaviour in case of handler for requested path is not found.
 */
public class UnsupportedPathCommand implements Command {
    private static final Logger logger = Logger.getLogger(LogoutCommand.class);



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.warn(REQUESTED_UNSUPPORTED_URI );
        response.sendRedirect(request.getContextPath() + HOME_PATH);
        return REDIRECTED;
    }
}
