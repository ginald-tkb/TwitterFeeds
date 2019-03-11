package com.company.models;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by tshiamo on 2019/03/10.
 */
public class TweetTest {
    @Test
    public void tweetObjTest() throws Exception {
        Tweet tweet = new Tweet(new User("Tshiamo"), "My tweet string");
        assertEquals("My tweet string", tweet.getText());
        assertNotNull(tweet.getAuthor());
        assertEquals("Tshiamo", tweet.getAuthor().getName());
        tweet.setText("My String Tweet");
        assertEquals("My String Tweet", tweet.getText());
    }

}