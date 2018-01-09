package ua.kiyv.training.testingSystem;

import ua.kiyv.training.testingSystem.dao.*;
import ua.kiyv.training.testingSystem.model.entity.Option;

/**
 * Created by Tanya on 03.01.2018.
 */
public class Apps {
    public static void main(String[] args) {
        UserDao userDao=DaoFactory.getInstance().createUserDao();
        UserDao userDao1=DaoFactory.getInstance().createUserDao();
        QuestionDao questionDao = DaoFactory.getInstance().createQuestionDao();
        TopicDao topicDao=DaoFactory.getInstance().createTopicDao();
        OptionDao optionDao = DaoFactory.getInstance().createOptionDao();
        AssessmentDao assessmentDao=DaoFactory.getInstance().createAssesmentDao();
        //User user=userDao.findById(2);
        //User user=new User(6,"Vasiliy","t","t","t","q", User.Role.ADMIN);
       // userDao.create(user);
       //System.out.println(userDao.findById(2));
//        System.out.println(userDao.equals(userDao1));
        //System.out.println(userDao.update(user));
        //userDao.update(user);
//        User user=userDao.findById(2);
//        System.out.println(userDao.findByRole(User.Role.ADMIN));
//        OptionDao optionDao=ServiceFactory.getInstance().createOptionDao();
//        System.out.println(optionDao.findById(1));
        //Question question=new Question(7,"Capital of France",1);
//        questionDao.create(question);
       // Question question1=questionDao.findById(7);
        //question1.setQuestionText("Capital of Greate Britain");
//        System.out.println(questionDao.findAll());
        //questionDao.delete(question1);
        /** Topic****
        Topic topic = new Topic("Geography","includes different info about..,");
        topicDao.create(topic);*/
        //topicDao.create(new Topic("History","hgjlf"));
        //System.out.println(topicDao.findAll());
        //System.out.println(topicDao.findById(1));
        //Topic topic=topicDao.findById(3);
        //topic.setInfo("Excellent knowledge you need");
        //topicDao.update(topic);
        //topicDao.delete(topic);

        /** Option****
         */
        //Option option = new Option("Paris");

        Option option = new Option("Paris",1,true,"bla-bla-bla",1,1);
    //Option option1 =optionDao.findById(3);
   // option1.setComment("Wonderful");
        //System.out.println(optionDao.findAll());
        //optionDao.update(option1);
        //optionDao.delete(option1);
       optionDao.create(option);
        //System.out.println(option1.getAssesmentId());

        /** Assessment****
         */

//        Assessment assessment= assessmentDao.findById(3);
//        assessment.setTopicId(1);
        //System.out.println(assessment);
        //assessmentDao.create(assessment);
        //assessmentDao.delete(assessment);
       // System.out.println(assessmentDao.findAll());
//        assessmentDao.update(assessment);




    }
}
