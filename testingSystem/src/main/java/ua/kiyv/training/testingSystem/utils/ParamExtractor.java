package ua.kiyv.training.testingSystem.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tanya on 15.01.2018.
 */
public class    ParamExtractor {

    public static final String NUMBER_BETWEEN_SLASHES_PATTERN = "\\d+(?=/|$)";
    private Pattern numberPattern = Pattern.compile(NUMBER_BETWEEN_SLASHES_PATTERN);

    public int extractSingleIntPathParam(HttpServletRequest request){
        String uri = request.getRequestURI();
        Matcher matcher = numberPattern.matcher(uri);
        if(matcher.find()) {
            return extractIntFromString(matcher.group());
        }
        return 0;
    }

    public int extractIntFromString(String str){
        int result=0;
        try {
            result = Integer.parseInt(str);
        }
        catch (NumberFormatException ex){}
        return result;
    }
}
