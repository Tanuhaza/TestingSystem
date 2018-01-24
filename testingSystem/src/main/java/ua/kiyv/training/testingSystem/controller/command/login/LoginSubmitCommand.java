package ua.kiyv.training.testingSystem.controller.command.login;



import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserService;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginSubmitCommand extends CommandWrapper {
    private static final String PARAM_LOGIN = "login_name";
    private static final String PARAM_PASSWORD ="login_password";

    private UserService userService = ServiceFactory.getInstance().createUserService();

    public LoginSubmitCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        saveLoginDataToRequest(request);
        String pageToGo = PagesPath.LOGIN_PATH;
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        Optional<User> user = userService.getUserByLoginPassword(login, password);
        if( user.isPresent() ){
            User person = user.get();
            pageToGo = getResultPageByUserRole(person);
            request.getSession().setAttribute(Attributes.USER_ID, person.getId());
            request.getSession().setAttribute(Attributes.USER_ROLE, person.getRole());
        }
        clearLoginDataFromRequest(request);
        return pageToGo;
    }

    private String getResultPageByUserRole(User user){
        String result = PagesPath.HOME_PATH;
        if(user.getRole()== User.Role.ADMIN) {
            result = PagesPath.ADMIN_PATH;
        }

        return result;
    }

    private void saveLoginDataToRequest(HttpServletRequest request){
        request.setAttribute(Attributes.PREVIOUS_LOGIN, request.getParameter(PARAM_LOGIN));
        request.setAttribute(Attributes.PREVIOUS_PASSWORD, request.getParameter(PARAM_PASSWORD));
    }

    private void clearLoginDataFromRequest(HttpServletRequest request){
        request.removeAttribute(Attributes.PREVIOUS_LOGIN);
        request.removeAttribute(Attributes.PREVIOUS_PASSWORD);
    }
}