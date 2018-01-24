package ua.kiyv.training.testingSystem.controller.command.user.topic;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Topic;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tanya on 11.01.2018.
 */
public class ViewTopicsCommand extends CommandWrapper {

    public ViewTopicsCommand() {
        super(PagesPath.LOGIN_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Topic> topics = ServiceFactory.getInstance().createConstructingTestService().findAllTopics();
        request.setAttribute(Attributes.TOPICS, topics);
        return PagesPath.TOPICS_PAGE;

    }
}
