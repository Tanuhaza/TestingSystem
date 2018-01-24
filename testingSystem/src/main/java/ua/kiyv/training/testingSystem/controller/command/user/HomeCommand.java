package ua.kiyv.training.testingSystem.controller.command.user;

import ua.kiyv.training.testingSystem.controller.Command;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static ua.kiyv.training.testingSystem.utils.constants.Attributes.PAGE_TITLE;
import static ua.kiyv.training.testingSystem.utils.constants.Attributes.USER;
import static ua.kiyv.training.testingSystem.utils.constants.Attributes.USER_ID;
import static ua.kiyv.training.testingSystem.utils.constants.MessageKeys.TITLE_HOME;

/**
 * Created by Tanya on 06.01.2018.
 */
public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{

        request.setAttribute(PAGE_TITLE, TITLE_HOME);
        Integer userId = (Integer) request.getSession().getAttribute(USER_ID);
        Optional<User> user = ServiceFactory.getInstance().createUserService().findById(userId);
        if (user.isPresent()){
            User person = user.get();
            request.setAttribute(USER, person);
        }
        
        return PagesPath.HOME_PAGE;

    }
}
