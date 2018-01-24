package ua.kiyv.training.testingSystem.model.entity;

/**
 * Created by Tanya on 01.01.2018.
 */
public class Topic {

    private int id;
    private String title;
    private String info;

    public Topic() {
    }

    public Topic(String title, String info) {
        this.title = title;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (id != topic.id) return false;
        if (title != null ? !title.equals(topic.title) : topic.title != null) return false;
        return info != null ? info.equals(topic.info) : topic.info == null;
    }

    @Override
    public int hashCode(){
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public static class Builder {
        private Topic topic;

        public Builder() {
            topic = new Topic();
        }

        public Topic.Builder setId(int id) {
            topic.setId(id);
            return this;
        }

        public Topic.Builder setTitle(String title) {
            topic.setTitle(title);
            return this;
        }

        public Topic.Builder setInfo(String info) {
            topic.setInfo(info);
            return this;
        }

        public Topic build() {
            return topic;
        }
    }
}
