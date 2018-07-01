package com.hateit.models;

import com.hateit.common.PersianCalendar;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class Comment {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Post post;

    @DatabaseField(dataType = DataType.LONG_STRING)
    private String content;

    @DatabaseField
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate()
    {
        return PersianCalendar.getPersianDate(getTimestamp());
    }

}
