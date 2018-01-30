package ua.kiyv.training.testingSystem.service.impl;

import ua.kiyv.training.testingSystem.service.ConstructingQuizService;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserResponseService;
import ua.kiyv.training.testingSystem.service.UserService;

/**
 * Created by Tanya on 05.01.2018.
 */
public class ServiceFactoryImpl extends ServiceFactory {
    @Override
    public UserService createUserService() {
        return new UserServiceImpl();
    }

    @Override
    public ConstructingQuizService createConstructingQuizService() {
        return new ConstructingQuizServiceImpl();
    }

    @Override
    public UserResponseService createUserResponseService() {
        return new UserResponseServiceImpl();
    }
}
