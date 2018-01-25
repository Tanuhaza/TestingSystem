package ua.kiyv.training.testingSystem.utils.constants;

/**
 * This class contains all necessary messages to perform logging
 */
public class LoggerMessages {
    public static final String REQUESTED_UNSUPPORTED_URI = "Requested unsupported URI. Redirecting to home page.";
    public static final String ERROR_PARSING_INPUT_DATE = "Error occured when parsing date...";
    public static final String SUCCESSFULL_USER_INFO_PARSE = "User's info was successfully parsed";
    public static final String SUCCESSFULL_REFILLING = "Card was successfully refilled";
    public static final String SUCCESSFULL_CARD_TRANSFER = "Card transfer was successfully done";
    public static final String SUCCESSFULL_ACCOUNT_TRANSFER = "Account transfer was successfully done";
    public static final String SUCCESSFUL_REGISTER = "Congratulations! You was successfully registered!";

    public static final String PROBLEM_WITH_DB_CONFIG= "Problem wit DB config file";
    public static final String CAN_NOT_GET_DB_CONNECTION = "Can not get connection to DB";
    public static final String CAN_NOT_CLOSE_CONNECTION = "Can not close connection";
    public static final String CAN_NOT_PREPARE_STATEMENT_WITH_AUTO_GENERATED_KEYS = "Can not prepare statement with auto generated keys";
    public static final String CAN_NOT_PREPARE_STATEMENT =  "Can't prepare statement";
    public static final String CAN_NOT_CREATE_STATEMENT = "Can't create statement";
    public static final String CAN_NOT_SET_IN_TRANSACTION = "Can't set is-in-transaction";
    public static final String CAN_NOT_COMMIT_IN_TRANSACTION= "Can't commit: Is not in transaction";
    public static final String CAN_NOT_COMMIT = "Can't commit";
    public static final String CAN_NOT_ROLLBACK = "Can't rollback";
    public static final String CAN_NOT_COMMIT_TRANSACTION_NOT_BEGUN = "Can't commit transaction: it has not been begun";
    public static final String CAN_NOT_ROLLBACK_TRANSACTION_NOT_BEGUN = "Can't rollback transaction: it has not been begun";
    public static final String WRONG_TRANSACTION = "Transaction failed";

    public static final String ERROR_FIND_USER_BY_LOGIN = "Error occurred when finding user by login: ";
    public static final String ERROR_FIND_USER_BY_ID = "Error occurred when finding user by id: ";
    public static final String ERROR_FIND_ALL_USERS = "Error occurred when finding all users";
    public static final String ERROR_CREATE_NEW_USER = "Error occurred when creating new user: ";
    public static final String ERROR_UPDATE_USER = "Error occurred when updating user: ";
    public static final String ERROR_DELETE_USER = "Error occurred when deleting user: ";
    public static final String ERROR_FIND_ROLE = "Error occurred when finding user by role:";

    public static final String ERROR_FIND_USER_RESPONSES_BY_USER_ID_TEST_ID = "Error occurred when finding user response by userId and testID: ";
    public static final String ERROR_FIND_USER_RESPONSE_BY_USER_ID = "Error occurred when finding user response  by id: ";
    public static final String ERROR_FIND_ALL_USER_RESPONSES = "Error occurred when finding all user responses";
    public static final String ERROR_CREATE_NEW_USER_RESPONSE = "Error occurred when creating new user response: ";
    public static final String ERROR_UPDATE_USER_RESPONSE = "Error occurred when updating user response: ";
    public static final String ERROR_DELETE_USER_RESPONSE = "Error occurred when deleting user response: ";
    public static final String ERROR_FIND_PASSED_TESTS_ID_BY_USER_ID_PASSED_TIMES = "Error occurred when finding passed tests by userId and passedTimes: ";
    public static final String ERROR_FIND_PASSED_TIMES_BY_USER_ID_TEST_ID = "Error occurred when finding passedTimes: ";

    public static final String ERROR_FIND_TEST_BY_ID = "Error occurred when finding test by id: ";
    public static final String ERROR_FIND_ALL_TESTS = "Error occurred when finding all tests";
    public static final String ERROR_CREATE_NEW_TEST = "Error occurred when creating new test: ";
    public static final String ERROR_UPDATE_TEST= "Error occurred when updating test: ";
    public static final String ERROR_DELETE_TEST = "Error occurred when deleting test: ";
    public static final String ERROR_FIND_ASSOSIATED_QUESTIONS_BY_TEST_ID = "Error occurred when finding assosiated questions by test id";
    public static final String ERROR_FIND_FIND_ASSOSIATED_TESTS_BY_TOPIC_ID = "Error occurred when finding assosiated tests by topic id";
    public static final String ERROR_ASSOSIATE_TEST_WITH_QUESTION = "Error occurred when assosiate test with question";

    public static final String ERROR_FIND_OPTION_BY_ID = "Error occurred when finding option by id: ";
    public static final String ERROR_FIND_ALL_OPTIONS = "Error occurred when finding all options";
    public static final String ERROR_CREATE_NEW_OPTION= "Error occurred when creating new option: ";
    public static final String ERROR_UPDATE_OPTION = "Error occurred when updating option: ";
    public static final String ERROR_DELETE_OPTION = "Error occurred when deleting option: ";

    public static final String ERROR_FIND_TOPIC_BY_ID = "Error occurred when finding topic by id: ";
    public static final String ERROR_FIND_ALL_TOPICS = "Error occurred when finding all topics";
    public static final String ERROR_CREATE_NEW_TOPIC = "Error occurred when creating new topic: ";
    public static final String ERROR_UPDATE_TOPIC= "Error occurred when updating topic: ";
    public static final String ERROR_DELETE_TOPIC = "Error occurred when deleting topic: ";

    public static final String ERROR_FIND_QUESTION_BY_ID = "Error occurred when finding question by id: ";
    public static final String ERROR_FIND_ALL_QUESTIONS = "Error occurred when finding all questions";
    public static final String ERROR_CREATE_NEW_QUESTION = "Error occurred when creating new question: ";
    public static final String ERROR_UPDATE_QUESTION= "Error occurred when updating question: ";
    public static final String ERROR_DELETE_QUESTION = "Error occurred when deleting question: ";
    public static final String ERROR_FIND_ASSOSIATED_QUESTIONS_BY_TOPIC_ID = "Error occurred when finding assosiated questions by topic id";
    public static final String ERROR_FIND_FIND_ASSOSIATED_OPTIONS_BY_QUESTION_ID = "Error occurred when finding assosiated options by question id";
    public static final String ERROR_FIND_FIND_ASSOSIATED_TESTS_BY_QUESTION_ID ="Error occurred when finding assosiated tests by question id";

    public static final String UNKNOWN_ERROR_OCCURED = "Unknown error occurred";
    public static final String SERVICE_EXCEPTION_OCCURRED = "exception in business logic";
    public static final String APPLICATION_EXCEPTION_OCCURRED = "application exception occurred";
    public static final String ERROR_USER_ALREADY_EXISTS = "User with such login already exists!";
}