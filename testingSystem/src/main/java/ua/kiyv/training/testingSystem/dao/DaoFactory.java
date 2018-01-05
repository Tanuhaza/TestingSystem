package ua.kiyv.training.testingSystem.dao;

import ua.kiyv.training.testingSystem.dao.Impl.JdbcDaoFactory;

/**
 * Created by Tanya on 02.01.2018.
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public  abstract TopicDao createTopicDao();
    public abstract OptionDao createOptionDao();
    public abstract QuestionDao createQuestionDao();
    public abstract AssessmentDao createAssesmentDao();

    public static DaoFactory getInstance() {
        if(daoFactory==null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JdbcDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
