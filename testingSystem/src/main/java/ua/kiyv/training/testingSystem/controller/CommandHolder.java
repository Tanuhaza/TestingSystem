package ua.kiyv.training.testingSystem.controller;


import ua.kiyv.training.testingSystem.controller.command.login.LoginCommand;
import ua.kiyv.training.testingSystem.controller.command.login.LoginSubmitCommand;
import ua.kiyv.training.testingSystem.controller.command.user.HomeCommand;
import ua.kiyv.training.testingSystem.controller.command.user.LogoutCommand;
import ua.kiyv.training.testingSystem.controller.command.user.RegisterSubmitCommand;
import ua.kiyv.training.testingSystem.controller.command.user.UnsupportedPathCommand;

import java.util.HashMap;
import java.util.Map;

import static ua.kiyv.training.testingSystem.utils.constants.PagesPath.*;

/**
 * This class is implementation of CommandHolder. It defines command for every supported request uri.
 *
 * @author oleksij.onysymchuk@gmail.com
 */
class CommandHolder {

    static final String DELIMITER = ":";
    private static final String GET = "GET" + DELIMITER;
    private static final String POST = "POST" + DELIMITER;

    private final Command unsupportedPathCommand = new UnsupportedPathCommand();

    private final String deployPath;

    /**
     * Holder for GET commands
     */
    private Map<String, Command> commands = new HashMap<>();

    CommandHolder(String deployPath) {
        this.deployPath = deployPath;
        init();
    }

    private void init() {

        commands.put(GET + deployPath + HOME_PATH, new HomeCommand());
        commands.put(GET + deployPath + LOGIN_PATH, new LoginCommand());

        commands.put(GET + deployPath + LOGOUT_PATH, new LogoutCommand());
//        commands.put(GET + deployPath + REGISTER_PATH, new RegisterSubmitCommand());
//        commands.put(GET + deployPath + USER_PURCHASE_PATH, new UserPurchaseCommand());
//        commands.put(GET + deployPath + USER_ORDER_HISTORY_PATH, new UserOrderHistoryCommand());
//
//        commands.put(GET + deployPath + ADMIN_REFILL_PATH, new AdminRefillCommand());
//        commands.put(GET + deployPath + ADMIN_ADD_CREDITS_PATH, new AdminAddCreditCommand());
//
//
        commands.put(POST + deployPath + LOGIN_PATH, new LoginSubmitCommand());
        commands.put(POST + deployPath + REGISTER_PATH, new RegisterSubmitCommand());
//        commands.put(POST + deployPath + USER_PURCHASE_PATH, new UserPurchaseSubmitCommand());
//
//        commands.put(POST + deployPath + ADMIN_REFILL_PATH, new AdminRefillSubmitCommand());
//        commands.put(POST + deployPath + ADMIN_ADD_CREDITS_PATH, new AdminAddCreditSubmitCommand());

    }

    /**
     * @param commandKey Key of the command, mapped to certain uri and request method
     * @return Command instance, mapped to certain uri and request method
     */
    Command findCommand(String commandKey) {
        return commands.getOrDefault(commandKey, unsupportedPathCommand);
    }

}
