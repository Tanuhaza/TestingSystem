package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.dao.*;
import ua.kiyv.training.testingSystem.service.*;

/**
 * Created by Tanya on 05.01.2018.
 */
public class ServiceFactoryImpl extends ServiceFactory{
    @Override
    public UserSevice createUserService() {
        return new UserServiceImpl();
    }

    @Override
    public TopicService createTopicService() {
        return new TopicServiceImpl();
    }

    @Override
    public OptionService createOptionService() {return new OptionServiceImpl();}

    @Override
    public QuestionService createQuestionService() {
        return new QuestionServiceImpl();
    }

    @Override
    public AssessmentService createAssesmentService() {
        return new AssessmentServiceImpl();
    }
}
