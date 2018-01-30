package ua.kiyv.training.testingSystem.model.entity;

/**
 * Created by Tanya on 14.01.2018.
 */
public class Quiz {
        private int id;
        private String name;
        private int topicId;

    public Quiz() {
    }

    public Quiz(String name, int topicId) {
        this.name = name;
        this.topicId = topicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Quiz quiz = (Quiz) o;

        if (id != quiz.id) return false;
        if (topicId != quiz.topicId) return false;
        return (name != null ? quiz.equals(quiz.name) : (quiz.name == null));
    }

    @Override
    public int hashCode(){
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + topicId;
        return result;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name +
                '}'+'\n';
    }

    public static class Builder {
        private Quiz quiz;

        public Builder() {
            quiz = new Quiz();
        }

        public Quiz.Builder setId(int id) {
            quiz.setId(id);
            return this;
        }

        public Quiz.Builder setName(String name) {
            quiz.setName(name);
            return this;
        }

        public Quiz.Builder setTopicId(int id) {
            quiz.setTopicId(id);
            return this;
        }

        public Quiz build() {
            return quiz;
        }
    }
}
