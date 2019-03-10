package com.company.models;

/**
 * Created by tshiamo on 2019/03/09.
 */
public class Tweet {
    private User author;
    private String text;

    public Tweet(User author, String text) {
        this.author = author;
        this.text = text;
    }

    public void setAuthor(User author) {
        author.addTweet(this);
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }
}
