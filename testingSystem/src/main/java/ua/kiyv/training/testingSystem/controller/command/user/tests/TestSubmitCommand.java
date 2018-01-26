package ua.kiyv.training.testingSystem.controller.command.user.tests;

import ua.kiyv.training.testingSystem.controller.CommandWrapper;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.UserResponse;
import ua.kiyv.training.testingSystem.service.ServiceFactory;
import ua.kiyv.training.testingSystem.service.UserResponseService;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Created by Tanya on 15.01.2018.
 */
public class TestSubmitCommand extends CommandWrapper {

    public TestSubmitCommand() {super(PagesPath.LOGIN_PAGE);}

    String pageToGo = PagesPath.LOGIN_PATH;

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       Integer testId = (Integer)request.getSession().getAttribute(Attributes.TEST_ID);
       Integer topicId = (Integer)request.getSession().getAttribute(Attributes.TOPIC_ID);
       Integer userId = (Integer)request.getSession().getAttribute(Attributes.USER_ID);

       Map<Question, List<Option>> testMap = ServiceFactory.getInstance()
               .createConstructingTestService().getQuestionOptionsMapByTestID((int) testId);

       Map<Question, List<Option>> userResultMap = getUserResultMap(request,testMap);

       int totalScore =ServiceFactory.getInstance().createUserResponseService().getTotalScore(userResultMap);

       saveUserResultMap(userResultMap,userId,topicId,testId,totalScore);

        request.setAttribute("result", userResultMap);
        request.setAttribute("test", testMap);
        request.setAttribute("sum", totalScore);
        request.getRequestDispatcher(PagesPath.RESPONSE_PAGE).forward(request, response);
    return PagesPath.FORWARD; }


    public List<Integer> getUserOptionsIdByQuestion(HttpServletRequest request, Question question) {
        String questionId = String.valueOf(question.getId());
        return Arrays.asList(request.getParameterValues(questionId))
                .stream()
                .map(n -> Integer.valueOf(n))
                .collect(Collectors.toList());
    }

    public List<Option> getUserOptions(List<Option> options, List<Integer> userOptionsId){
        return options.stream()
                .filter(option ->userOptionsId.contains(option.getId()))
                .collect(Collectors.toList());
    }

    public Map<Question,List<Option>> getUserResultMap(HttpServletRequest request,Map<Question,List<Option>> testMap){
      return testMap.keySet().stream()
              .collect(Collectors.toMap(Function.identity(),
                      question->getUserOptions(testMap.get(question),getUserOptionsIdByQuestion(request,question))));

    }

    public void saveUserResponseDate(int userId, int topicId, int testId, Question question, Option option,int passedTimes, int sum ){
        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
        UserResponse userResponse= new UserResponse.Builder()
                            .setUserId(userId)
                            .setTestId(testId)
                            .setTopicId(topicId)
                            .setQuestionId(question.getId())
                            .setOptionId(option.getId())
                            .setTotalScore(sum)
                            .setPassedTimes(passedTimes)
                            .build();
        userResponseService.create(userResponse);
        }

        public int definedPassedTimes(int userId,int testId){
            UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
            List<Integer> passedTimesList =  userResponseService.getPassedTimes(userId,testId);
            int passedTimes = 0;
            if ((passedTimesList.contains(2)&& passedTimesList.contains(1))||(passedTimesList.contains(1))){
               passedTimes = 2;} else {passedTimes = 1;}

            if (passedTimesList.contains(2)){
                userResponseService.deleteByPassedTimes(userId,testId,2);}
            return passedTimes;
        }

    public void saveUserResultMap(Map<Question,List<Option>> userResultMap,int userId, int topicId, int testId,int totalScore ){
        int passedTimes = definedPassedTimes(userId,testId);
            userResultMap.entrySet()
                .stream()
                .forEach(e -> e.getValue()
                        .stream()
                        .forEach(option -> saveUserResponseDate(userId,topicId,testId,e.getKey(),option,passedTimes,totalScore)));
    }
}
