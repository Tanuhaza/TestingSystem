package ua.kiyv.training.testingSystem.controller;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




/**
 * This class represents request dispatcher. It calls commands for correspondent request uri
 * and forwards request to the appropriate view page.
 *
 */
public class FrontController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FrontController.class);
    private static final String URI_IS = " : uri = ";

    private static final String DELIMITER = CommandHolder.DELIMITER;

    /**
     * Command's holder instance
     */
    private CommandHolder commandHolder;


    @Override
    public void init() throws ServletException {
        super.init();
        commandHolder = new CommandHolder(getServletContext().getContextPath());
        System.out.println(getServletContext().getContextPath());
    }

    /**
     * The main method, which redirects request to an appropriate page depends on commands results.
     *
     * @param request  request instance
     * @param response response instance
     * @throws IOException      in case of troubles with redirecting
     * @throws ServletException in case of internal servlet troubles. Do not used directly in application.
     */
    private String processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String commandKey = getMethod(request) + DELIMITER + getUri(request);
        Command command = commandHolder.findCommand(commandKey);

        String view = command.execute(request, response);
        return view;
    }

    private String getMethod(HttpServletRequest request) {
        return request.getMethod().toUpperCase();
    }

    private String getUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        logger.debug(request.getMethod().toUpperCase() + URI_IS + uri);
        return uri.toLowerCase();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = processRequest(request, response);
        if(!path.equals(PagesPath.REDIRECTED)) {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = processRequest(request, response);
        if(!path.equals(PagesPath.FORWARD))
            response.sendRedirect(path);
//        request.getRequestDispatcher(path).forward(request, response);
    }

    void setCommandHolder(CommandHolder commandHolder) {
        this.commandHolder = commandHolder;
    }
}
