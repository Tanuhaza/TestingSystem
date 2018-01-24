package ua.kiyv.training.testingSystem.service;

import ua.kiyv.training.testingSystem.service.impl.ServiceFactoryImpl;

/**
 * Created by Tanya on 02.01.2018.
 */
public abstract class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public abstract UserService createUserService();
    public abstract ConstructingTestService createConstructingTestService();
    public abstract UserResponseService createUserResponseService();

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
