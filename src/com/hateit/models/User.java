package com.hateit.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;

public class User {

    @DatabaseField(id=true)
    private String id;

    @DatabaseField
    private String username;

    @DatabaseField
    private String Password;

    @DatabaseField
    private long birthDate;

    @DatabaseField
    private String location;

    @DatabaseField
    private String shortDescription;

    @DatabaseField
    private String image;

    @DatabaseField
    private String name;

    @ForeignCollectionField(eager = true, orderColumnName = "timestamp")
    private ForeignCollection<Post> posts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ForeignCollection<Post> getPosts() {
        return posts;
    }

    public void setPosts(ForeignCollection<Post> posts) {
        this.posts = posts;
    }

    public int getValue()
    {
        int value = 0;
//        for(Post p:getPosts())
//        {
//            value += 2;
//            value += 2*2;
//        }
        return value;
    }

    public long getAge()
    {
        return ((System.currentTimeMillis()/1000 - getBirthDate()))/(60*60*24*365);
    }

    public boolean getCanDelete()
    {
        return getValue() > 2000;
    }

    public boolean getCanChangeCategories()
    {
        return getValue() > 2500;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
