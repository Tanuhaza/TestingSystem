package ua.kiyv.training.testingSystem.controller;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.controller.validate.Errors;
import ua.kiyv.training.testingSystem.service.ServiceException;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.LoggerMessages;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;
import ua.kiyv.training.testingSystem.exception.ApplicationException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * wrapper class for specific commands which can throw exception
 */
public abstract class CommandWrapper implements Command {
    private static final Logger logger = Logger.getLogger(CommandWrapper.class);
    private final String nextPage;

    protected CommandWrapper(String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * main method which wrap all actions, which could throw some exception
     *
     * @param request  request from client
     * @param response response to client
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            return performExecute(request, response);
        } catch (ServiceException exception) {
            logger.error(LoggerMessages.SERVICE_EXCEPTION_OCCURRED, exception);
            putErrorMessageInRequest(request, exception.getMessageKey());
            request.getRequestDispatcher(PagesPath.ERROR_PAGE).forward(request, response);
        } catch (ApplicationException exception) {
            logger.error(LoggerMessages.APPLICATION_EXCEPTION_OCCURRED, exception);
            putErrorMessageInRequest(request, exception.getMessageKey());
            request.getRequestDispatcher(PagesPath.ERROR_PAGE).forward(request, response);
        } catch (Exception exception) {
            logger.error(LoggerMessages.UNKNOWN_ERROR_OCCURED, exception);
            putErrorMessageInRequest(request, MessageKeys.UNKNOWN_ERROR_OCCURED);
            request.getRequestDispatcher(PagesPath.ERROR_PAGE).forward(request, response);
        }
        return PagesPath.FORWARD;
    }

    public void putErrorMessageInRequest(HttpServletRequest request, String messageKey) {
        Errors errors = (Errors) request.getAttribute(Attributes.ERRORS);
        if (errors == null) {
            errors = new Errors();
        }
        errors.addError(Attributes.ERROR, messageKey);
        request.setAttribute(Attributes.ERRORS, errors);
    }

    public abstract String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}