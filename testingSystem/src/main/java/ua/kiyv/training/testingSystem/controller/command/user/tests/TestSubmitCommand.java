package ua.kiyv.training.testingSystem.controller.command.user.tests;

import com.sun.javafx.collections.MappingChange;
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

import static ua.kiyv.training.testingSystem.utils.constants.Attributes.IS_QUESTION_CHECKED;
import static ua.kiyv.training.testingSystem.utils.constants.PagesPath.PARTIAL_RESPONSE_PAGE;


/**
 * Created by Tanya on 15.01.2018.
 */
public class TestSubmitCommand extends CommandWrapper {

        public TestSubmitCommand() {super(PagesPath.LOGIN_PAGE);}

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       Integer quizId = (Integer)request.getSession().getAttribute(Attributes.QUIZ_ID);
       Integer topicId = (Integer)request.getSession().getAttribute(Attributes.TOPIC_ID);
       Integer userId = (Integer)request.getSession().getAttribute(Attributes.USER_ID);
       Map<Question, List<Option>> quizMap = ServiceFactory.getInstance()
               .createConstructingQuizService().getQuestionOptionsMapByQuizID((int)quizId);
       if (!isQuestionChecked(request,quizMap.keySet())){
           boolean isQuestionChecked = false;
           request.setAttribute(IS_QUESTION_CHECKED, isQuestionChecked);
           Map<Question, List<Option>> partialResultMap =getPartialUserResultMap(request,quizMap,getPartialUserQuestions(request,quizMap.keySet()));
           request.setAttribute(Attributes.QUIZ, quizMap);
           request.setAttribute("result", partialResultMap);
           request.getRequestDispatcher( PARTIAL_RESPONSE_PAGE).forward(request,response);
       }
       Map<Question, List<Option>> userResultMap = getUserResultMap(request,quizMap);
       int totalScore =ServiceFactory.getInstance().createUserResponseService().getTotalScore(userResultMap);
       saveUserResultMap(userResultMap,userId,topicId,quizId,totalScore);

        request.setAttribute("result", userResultMap);
        request.setAttribute("quiz", quizMap);
        request.setAttribute("sum", totalScore);
        request.getRequestDispatcher(PagesPath.RESPONSE_PAGE).forward(request, response);
    return PagesPath.FORWARD; }

        private List<Question> getPartialUserQuestions(HttpServletRequest request, Set<Question> questions){
        return  questions
                .stream()
                .filter(question -> getParametersValueByQuestion(request,question)!=null)
                .collect(Collectors.toList());
    }

    private Map<Question, List<Option>> getPartialUserResultMap(HttpServletRequest request, Map<Question, List<Option>> quizMap,List<Question> questions){
        return  quizMap.keySet()
                .stream().filter(question -> questions.contains(question)).collect(Collectors.toMap(Function.identity(),
                question->getUserOptions(quizMap.get(question),getUserOptionsIdByQuestion(request,question))));
    }

    private boolean isQuestionChecked(HttpServletRequest request,Set<Question> questions) {
        return questions
                .stream()
                .allMatch(question -> getParametersValueByQuestion(request, question) != null);
    }

    private String[] getParametersValueByQuestion(HttpServletRequest request,Question question){
        return  (request.getParameterValues(String.valueOf(question.getId())));
    }

    private Map<Question,List<Option>> getUserResultMap(HttpServletRequest request,Map<Question,List<Option>> quizMap){
        return quizMap.keySet().stream()
                .collect(Collectors.toMap(Function.identity(),
                        question->getUserOptions(quizMap.get(question),getUserOptionsIdByQuestion(request,question))));
    }

    private List<Option> getUserOptions(List<Option> options, List<Integer> userOptionsId){
        return options.stream()
                .filter(option ->userOptionsId.contains(option.getId()))
                .collect(Collectors.toList());
    }

    private List<Integer> getUserOptionsIdByQuestion(HttpServletRequest request, Question question) {
    return   Arrays.asList(getParametersValueByQuestion(request,question))
                .stream()
                .map(n -> Integer.valueOf(n))
                .collect(Collectors.toList());
    }

    private void saveUserResultMap(Map<Question,List<Option>> userResultMap,int userId, int topicId, int quizId,int totalScore ){
        int passedTimes = definedPassedTimes(userId,quizId);
        userResultMap.entrySet()
                .forEach(e -> e.getValue()
                        .forEach(option -> saveUserResponseDate(userId,topicId,quizId,e.getKey(),option,passedTimes,totalScore)));
    }

    private int definedPassedTimes(int userId,int quizId){
        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
        List<Integer> passedTimesList =  userResponseService.getPassedTimes(userId,quizId);
        int passedTimes = 0;
        if ((passedTimesList.contains(2)&& passedTimesList.contains(1))||(passedTimesList.contains(1))){
            passedTimes = 2;} else {passedTimes = 1;}

        if (passedTimesList.contains(2)){
            userResponseService.deleteByPassedTimes(userId,quizId,2);}
        return passedTimes;
    }

    private void saveUserResponseDate(int userId, int topicId, int quizId, Question question, Option option,int passedTimes, int sum ){
        UserResponseService userResponseService = ServiceFactory.getInstance().createUserResponseService();
        UserResponse userResponse= new UserResponse.Builder()
                            .setUserId(userId)
                            .setTestId(quizId)
                            .setTopicId(topicId)
                            .setQuestionId(question.getId())
                            .setOptionId(option.getId())
                            .setTotalScore(sum)
                            .setPassedTimes(passedTimes)
                            .build();
        userResponseService.create(userResponse);
        }




}
