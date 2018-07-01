package com.hateit.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;


public class HateItException extends RuntimeException {
    private ArrayList<String> messages = new ArrayList<>();
    private String backLink;
    private String backTitle;

    public HateItException(String backLink, String backTitle) {
        this.backLink = backLink;
        this.backTitle = backTitle;
    }

    public HateItException(String backLink, String backTitle, String message) {
        this.backLink = backLink;
        this.backTitle = backTitle;
        this.messages.add(message);
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public String getBackLink() {
        return backLink;
    }

    public void setBackLink(String backLink) {
        this.backLink = backLink;
    }

    public String getBackTitle() {
        return backTitle;
    }

    public void setBackTitle(String backTitle) {
        this.backTitle = backTitle;
    }

    public boolean isHappen()
    {
        return getMessages().size() > 0;
    }
}
