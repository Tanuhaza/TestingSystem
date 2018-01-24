package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.dao.*;

/**
 * Created by Tanya on 02.01.2018.
 */

public class JdbcDaoFactory  extends DaoFactory{
    private UserDao userDao;

    @Override
    public UserDao createUserDao() {
        if (userDao==null) {
            userDao = new JdbcUserDao();
        }
        return userDao;
    }

    @Override
    public TopicDao createTopicDao() {
        return new JdbcTopicDao();
    }

    @Override
    public TestDao createTestDao() {
        return new JdbcTestDao();
    }

    @Override
    public OptionDao createOptionDao() {
        return new JdbcOptionDao();
    }

    @Override
    public QuestionDao createQuestionDao() {return new JdbcQuestionDao();}

    @Override
    public UserResponseDao createUserResponseDao() {return new JdbcUserResponseDao();}
}
