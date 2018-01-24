package ua.kiyv.training.testingSystem.model.entity;

import java.util.Date;

/**
 * Created by Tanya on 02.01.2018.
 */
public class UserResponse {


    private int userId;
    private int questionId;
    private int optionId;
    private int testId;
    private int topicId;
    private Date datePassed;
    private int totalScore;
    private int passedTimes;

    public UserResponse() {
    }

    public UserResponse(int userId, int questionId, int optionId, int testId, int topicId, int totalScore) {
        this.userId = userId;
        this.questionId = questionId;
        this.optionId = optionId;
        this.testId = testId;
        this.topicId = topicId;
        this.totalScore = totalScore;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPassedTimes() {return passedTimes;}

    public void setPassedTimes(int passedTimes) {this.passedTimes = passedTimes;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public Date getDatePassed() {
        return datePassed;
    }

    public void setDatePassed(Date datePassed) {
        this.datePassed = datePassed;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", testId=" + testId +
                ", questionId=" + questionId +
                ", optionId=" + optionId +
                ", datePassed=" + datePassed +
                ", totalScore=" + totalScore +
                ", passedTimes=" + passedTimes +
                '}' +'\n';
    }

    public static class Builder {
        private UserResponse assesment;

        public Builder() {
            assesment = new UserResponse();
        }

        public UserResponse.Builder setUserId(int userId) {
            assesment.setUserId(userId);
            return this;
        }

        public UserResponse.Builder setTopicId(int topicId) {
            assesment.setTopicId(topicId);
            return this;
        }

        public UserResponse.Builder setDataPassed(Date dataPassed) {
            assesment.setDatePassed(dataPassed);
            return this;
        }

        public UserResponse.Builder setTotalScore(int totalScore) {
            assesment.setTotalScore(totalScore);
            return this;
        }

        public UserResponse.Builder setTestId(int testId) {
            assesment.setTestId(testId);
            return this;
        }

        public UserResponse.Builder setQuestionId(int questionId) {
            assesment.setQuestionId(questionId);
            return this;
        }
        public UserResponse.Builder setOptionId(int optionId) {
            assesment.setOptionId(optionId);
            return this;
        }

        public UserResponse.Builder setPassedTimes(int passedTimes) {
            assesment.setPassedTimes(passedTimes);
            return this;
        }

        public UserResponse build() {
            return assesment;
        }
    }
}
