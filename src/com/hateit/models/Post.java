package com.hateit.models;

import com.hateit.common.PersianCalendar;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;


public class Post {

    @DatabaseField(id=true)
    private String id;

    @DatabaseField
    private String title;

    @DatabaseField(dataType = DataType.LONG_STRING)
    private String content;

    @DatabaseField
    private long timestamp;

    @ForeignCollectionField
    private ForeignCollection<Category> categories;

    @ForeignCollectionField(eager = true, orderColumnName = "timestamp", orderAscending = false)
    private ForeignCollection<Comment> comments;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Hate> hates;


    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private User user;

    @DatabaseField
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ForeignCollection<Comment> getComments() {
        return comments;
    }

    public void setComments(ForeignCollection<Comment> comments) {
        this.comments = comments;
    }

    public ForeignCollection<Hate> getHates() {
        return hates;
    }

    public void setHates(ForeignCollection<Hate> hates) {
        this.hates = hates;
    }

    public ForeignCollection<Category> getCategories() {
        return categories;
    }

    public void setCategories(ForeignCollection<Category> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getHatesCount()
    {
        return getHates().size();
    }

    public int getCommentsCount()
    {
        return getComments().size();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user+
                '}';
    }
}
