package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.dao.*;
import ua.kiyv.training.testingSystem.model.User;

/**
 * Created by Tanya on 02.01.2018.
 */

public class JdbcDaoFactory  extends DaoFactory{


    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao();
    }

    @Override
    public TopicDao createTopicDao() {
        return new JdbcTopicDao();
    }

    @Override
    public OptionDao createOptionDao() {
        return new JdbcOptionDao();
    }

    @Override
    public QuestionDao createQuestionDao() {
        return new JdbcQuestionDao();
    }

    @Override
    public AssesmentDao createAssesmentDao() {
        return new JdbcAssesmentDao();
    }
}
