package ua.kiyv.training.testingSystem.model.entity;

/**
 * Created by Tanya on 02.01.2018.
 */
public class Option {

    private int id;
    private String optionText;
    private int score;
    private boolean isCorrect;
    private String comment;
    private int questionId;

    public Option() {
    }

    public Option(String optionText) {
        this.optionText = optionText;

    }

    public Option(String optionText, int score, boolean isCorrect, String comment, int questionId) {
        this.optionText = optionText;
        this.score = score;
        this.isCorrect = isCorrect;
        this.comment = comment;
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", option='" + optionText + '\'' +
                ", score=" + score +
                ", isCorrect=" + isCorrect +
                ", comment='" + comment + '\'' +
                ", questionId=" + questionId +
                '}'+'\n';
    }

    public static class Builder {
        private Option option;

        public Builder() {
            option = new Option();
        }

        public Option.Builder setId(int id) {
            option.setId(id);
            return this;
        }

        public Option.Builder setOptionText(String optionText) {
            option.setOptionText(optionText);
            return this;
        }

        public Option.Builder setScore(int score) {
            option.setScore(score);
            return this;
        }

        public Option.Builder setCorrect(boolean isCorrect) {
            option.setCorrect(isCorrect);
            return this;
        }

        public Option.Builder setComment(String comment) {
            option.setComment(comment);
            return this;
        }

        public Option.Builder setQuestionId(int id) {
            option.setQuestionId(id);
            return this;
        }

        public Option build() {
            return option;
        }
    }
}
