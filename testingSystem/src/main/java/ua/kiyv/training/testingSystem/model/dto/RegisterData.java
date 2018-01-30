package ua.kiyv.training.testingSystem.model.dto;

/**
 * This class represents DTO to transfer register data to user instance
 */
public class RegisterData extends LoginData {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class Builder{
        RegisterData instance = new RegisterData();

        public Builder setFirstName(String firstName){
            instance.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName){
            instance.lastName = lastName;
            return this;
        }

        public Builder setLogin(String login){
            instance.login=login;
            return this;
        }

        public Builder setPassword(String password){
            instance.password=password;
            return this;
        }

        public Builder setEmail(String email){
            instance.email = email;
            return this;
        }

        public RegisterData build(){
            return instance;
        }
    }
}
