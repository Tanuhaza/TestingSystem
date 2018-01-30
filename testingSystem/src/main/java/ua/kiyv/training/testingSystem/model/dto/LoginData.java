package ua.kiyv.training.testingSystem.model.dto;

/**
 * This class represents DTO to transfer login data from controller layer to user instance
 */
public class LoginData {
    protected String login;
    protected String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LoginData() {}

    public LoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
