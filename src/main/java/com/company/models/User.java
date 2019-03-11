package com.company.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tshiamo on 2019/03/09.
 */
public class User implements Comparable<User> {
    private String name;
    /** All tweets by this user and the user's followers*/
    private List<Tweet> tweets;
    private List<User> followers;

    public User(String name) {
        this.name = name;
        this.tweets = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public void addFollower(User follower) {
        this.followers.add(follower);
    }

    public List<User> getFollowers() {
        return followers;
    }

    @Override
    public int compareTo(User u) {
        if (getName() == null || u.getName() == null) {
            return 0;
        }
        return getName().compareTo(u.getName());
    }
}
