package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.dao.*;
import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;
import ua.kiyv.training.testingSystem.service.impl.AssessmentServiceImpl;
import ua.kiyv.training.testingSystem.service.impl.ServiceFactoryImpl;

/**
 * Created by Tanya on 02.01.2018.
 */
public abstract class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public abstract UserSevice createUserService();
    public  abstract TopicService createTopicService();
    public abstract OptionService createOptionService();
    public abstract QuestionService createQuestionService();
    public abstract AssessmentService createAssesmentService();

    public static ServiceFactory getInstance() {
        if(serviceFactory==null) {
            synchronized (ServiceFactory.class) {
                if (serviceFactory == null) {
                    ServiceFactory temp = new ServiceFactoryImpl();
                    serviceFactory = temp;
                }
            }
        }
        return serviceFactory;
    }
}
