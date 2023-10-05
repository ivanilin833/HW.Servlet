package ru.netology.model;

public class Post {
    private long id;
    private String content;
    private boolean isRemove;

    public Post() {
    }

    public Post(long id, String content) {
        this.id = id;
        this.content = content;
        isRemove = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRemove() {
        return isRemove;
    }

    public void setRemove(boolean remove) {
        isRemove = remove;
    }
}
