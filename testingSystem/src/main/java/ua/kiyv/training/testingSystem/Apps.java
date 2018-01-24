package ua.kiyv.training.testingSystem;

import ua.kiyv.training.testingSystem.dao.DaoFactory;
import ua.kiyv.training.testingSystem.dao.UserResponseDao;
import ua.kiyv.training.testingSystem.model.entity.UserResponse;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserResponseService;

import java.util.*;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Tanya on 03.01.2018.
 */
public class Apps {
    public static void main(String[] args) {
//        UserDao userDao=DaoFactory.getInstance().createUserDao();
//        UserDao userDao1=DaoFactory.getInstance().createUserDao();
//        QuestionDao questionDao = DaoFactory.getInstance().createQuestionDao();
//        TopicDao topicDao=DaoFactory.getInstance().createTopicDao();
//        OptionService optionService = ServiceFactory.getInstance().createOptionService();
        UserResponseDao UserResponseDao= DaoFactory.getInstance().createUserResponseDao();
//        UserSevice userSevice = ServiceFactory.getInstance().createUserService();

//        System.out.println(userSevice.getTestsPassedByUser(1));
//        System.out.println(userSevice.getTotalScoreByUserAndTestId(1,2));
//        System.out.println(userSevice.getTestResultMapByUserId(1));
//         ResourceBundle bundle =
//                ResourceBundle.getBundle("text",new Locale("uk_UA"));
////        System.out.println();
//        System.out.println(bundle.getString("test.system"));


        //User user=userDao.findById(2);
       // User user=new User("Tatianka","t","t","t","q", User.Role.STUDENT);
        //userSevice.create(user);
        //System.out.println(userSevice.getUserByLoginPassword("vovan","1278").toString());
       //System.out.println(userDao.findById(2));
//        System.out.println(userDao.equals(userDao1));
        //System.out.println(userDao.update(user));
        //userDao.update(user);
//        User user=userDao.findById(2);
//        System.out.println(userDao.findByRole(User.Role.ADMIN));
//        OptionDao optionDao=ServiceFactory.getInstance().createOptionDao();
//        System.out.println(optionDao.findById(1));
        //Question question=new Question("Capital of France",1);
        // questionDao.create(question);
        //Question question1=questionDao.findById(10);
       // question1.setQuestionText("Capital of Great Britain");
        //questionDao.update(question1);
        //System.out.println(questionDao.getAssosiatedQuestionByTopicId(1));
        //System.out.println(question1);
        //question1.setQuestionText("Capital of Greate Britain");
       //System.out.println(questionDao.findAll());
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

       // Option option = new Option("Paris",1,true,"bla-bla-bla",1);
        //OptionDao optionDao=DaoFactory.getInstance().createOptionDao();
        //Option option2 = optionDao.findById(12);
       // System.out.println(optionDao.findById(1));
        //.setComment("+");

//        Optional<Option> option1 =optionService.findById(3);
//       Option option2 = option1.get();
//        option2.setComment("Wonderful");

        //System.out.println(option1);
        //System.out.println(optionService.findAll());
       // optionService.update(option2);
       // optionDao.delete(option2);
       //optionDao.create(option);
        //System.out.println(option1.getAssesmentId());

        /** UserResponse****
         */

//        UserResponse UserResponse= UserResponseDao.findById(3);
//        UserResponse.setTopicId(1);
//        UserResponse userResponse = new UserResponse(1,1,1,1,1,3);
        //System.out.println(UserResponse);
//        UserResponseDao.create(userResponse);
        //UserResponseDao.delete(UserResponse);
       // System.out.println(UserResponseDao.findAll());
//        UserResponseDao.update(UserResponse);
/** Test****
 */
//TestDao testDao=DaoFactory.getInstance().createTestDao();
//        Test test= new Test("Basic rules of Differentiation",2);
//testDao.create(test);
//        Test test1 = testDao.findById(1);
//        System.out.println(testDao.getAssosiatedTestsByTopicId(2));
//        test1.setName("Basic rules of Integration");

//        testDao.update(test1);
        UserResponse userResponse=new UserResponse();
        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
//        System.out.println(userResponseService.getPassedTimes(1,1));
//        System.out.println(UserResponseDao.findByUserId(3));
//        System.out.println(UserResponseDao.findAll());
        List<UserResponse> userResponses= UserResponseDao.findAll();
//        System.out.println(userResponses);

//       userResponse = userResponses.get(0);
////        System.out.println(userResponse);
//        userResponse.setOptionId(3);
//        userResponse.setTotalScore(1);
//        userResponseService.create(userResponse);
        System.out.println(userResponseService.getTestsPassedByUser(3)) ;
        System.out.println(userResponseService.getTotalScoreByPassedTimes(3,1,1)) ;

////        userResponse.setPassedTimes(0);
      //  userResponseService.update(userResponse);
//        System.out.println(userResponseService.isOptionResponseExist(1,1,2));



    }
}
