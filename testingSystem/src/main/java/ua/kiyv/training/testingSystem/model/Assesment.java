package ua.kiyv.training.testingSystem.model;

import java.util.Date;

/**
 * Created by Tanya on 02.01.2018.
 */
public class Assesment {

    private int id;
    private int userId;
    private int topicId;
    private Date datePassed;
    private int totalScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        return "Assesment{" +
                "id=" + id +
                ", userId=" + userId +
                ", topicId=" + topicId +
                ", datePassed=" + datePassed +
                ", totalScore=" + totalScore +
                '}';
    }

    public static class Builder {
        private Assesment assesment;

        public Builder() {
            assesment = new Assesment();
        }

        public Assesment.Builder setId(int id) {
            assesment.setId(id);
            return this;
        }

        public Assesment.Builder setUserId(int userId) {
            assesment.setUserId(userId);
            return this;
        }

        public Assesment.Builder setTopicId(int topicId) {
            assesment.setTopicId(topicId);
            return this;
        }

        public Assesment.Builder setDataPassed(Date dataPassed) {
            assesment.setDatePassed(dataPassed);
            return this;
        }

        public Assesment.Builder setTotalScore(int totalScore) {
            assesment.setTotalScore(totalScore);
            return this;
        }

        public Assesment build() {
            return assesment;
        }
    }
}
