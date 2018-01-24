package ua.kiyv.training.testingSystem.model.entity;

/**
 * Created by Tanya on 14.01.2018.
 */
public class Test{
        private int id;
        private String name;
        private int topicId;

    public Test() {
    }

    public Test(String name, int topicId) {
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

        Test test = (Test) o;

        if (id != test.id) return false;
        if (topicId != test.topicId) return false;
        return (name != null ? test.equals(test.name) : (test.name == null));
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
        return "Test{" +
                "id=" + id +
                ", name='" + name +
                '}'+'\n';
    }

    public static class Builder {
        private Test test;

        public Builder() {
            test = new Test();
        }

        public Test.Builder setId(int id) {
            test.setId(id);
            return this;
        }

        public Test.Builder setName(String name) {
            test.setName(name);
            return this;
        }

        public Test.Builder setTopicId(int id) {
            test.setTopicId(id);
            return this;
        }

        public Test build() {
            return test;
        }
    }
}
