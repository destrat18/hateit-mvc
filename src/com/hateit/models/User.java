package com.hateit.models;

import com.hateit.common.PersianCalendar;
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
    private int year;

    @DatabaseField
    private int month;

    @DatabaseField
    private int day;

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


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

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
        return "/image/"+image;
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
        if(getPosts().size() > 0)
            for(Post p:getPosts())
            {
                value += p.getCommentsCount()*2;
                value += p.getHatesCount();
            }
        return value;
    }

    public int getAge()
    {
        return (PersianCalendar.getDiffrentDayFromToday(getYear(), getMonth(), getDay())/365);
    }

    public String getMonthName()
    {
        return PersianCalendar.monthNames[getMonth()-1];
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
