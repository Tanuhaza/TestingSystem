package ua.kiyv.training.testingSystem.controller.validate;


import ua.kiyv.training.testingSystem.model.dto.RegisterData;
import ua.kiyv.training.testingSystem.utils.constants.Attributes;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.util.regex.Pattern;

public class UserValidator implements Validator<RegisterData> {
    private static final String REGEX_NAME="[A-Z]{1}[a-z]{1,}";
    private static final String REGEX_PASSWORD = "[A-Za-z0-9]{4,200}";
    private static final String REGEX_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_\\-]+@[a-z0-9_-]+(\\.[a-z0-9_\\-]+)*\\.[a-z]{2,6}$";

    @Override
    public Errors validate(RegisterData data) {
        Errors results = new Errors();
        if(!Pattern.matches(REGEX_NAME, data.getFirstName())){
            results.addError(Attributes.USER_NAME, MessageKeys.WRONG_USER_NAME);
        }
        if(!Pattern.matches(REGEX_NAME, data.getLastName())){
            results.addError(Attributes.USER_SURNAME, MessageKeys.WRONG_USER_SURNAME);
        }
        if(!Pattern.matches(REGEX_EMAIL, data.getEmail())){
            results.addError(Attributes.USER_CELLPHONE, MessageKeys.WRONG_USER_CELLPHONE);
        }
        if(!Pattern.matches(REGEX_PASSWORD, data.getPassword())){
            results.addError(Attributes.USER_PASSWORD, MessageKeys.WRONG_USER_PASSWORD);
        }

        return results;
    }
}