package ua.kiyv.training.testingSystem.model.entity;

import java.util.Date;

/**
 * Created by Tanya on 02.01.2018.
 */
public class Question {

    private int id;
    private String questionText;
    private Date creationalDate;
    private int topicId;

    public Question() {
    }

    public Question( int id, String questionText, int topicId) {
//        this.id = id;
        this.questionText = questionText;
//        this.creationalDate = creationalDate;
        this.topicId = topicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }


    public Date getCreationalDate() {
        return creationalDate;
    }

    public void setCreationalDate(Date creationalDate) {
        this.creationalDate = creationalDate;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question that = (Question) o;

        if (id != that.id) return false;
        if (topicId != that.topicId) return false;
        if (questionText != null ? !questionText.equals(that.questionText) : that.questionText != null) return false;
        return creationalDate != null ? creationalDate.equals(that.creationalDate) : that.creationalDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        result = 31 * result + (creationalDate != null ? creationalDate.hashCode() : 0);
        result = 31 * result + topicId;
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + questionText + '\'' +
                ", creationalDate=" + creationalDate +
                ", topicId=" + topicId +
                '}';
    }

    public static class Builder {
        private Question question;

        public Builder() {
            question = new Question();
        }

        public Question.Builder setId(int id) {
            question.setId(id);
            return this;
        }

        public Question.Builder setQuestionText(String questionText) {
            question.setQuestionText(questionText);
            return this;
        }

        public Question.Builder setCreationalDate(Date creationalDate) {
            question.setCreationalDate(creationalDate);
            return this;
        }

        public Question.Builder setTopicId(int topicId) {
            question.setTopicId(topicId);
            return this;
        }

        public Question build() {
            return question;
        }
    }
}
