package com.company.models;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by tshiamo on 2019/03/10.
 */
public class UserTest {
    @Test
    public void UserConstructorSetDefaultTest() throws Exception {
        User user = new User("Reginald");
        assertNotNull(user.getFollowers());
        assertNotNull(user.getTweets());
        assertEquals(0, user.getFollowers().size());
        assertEquals(0, user.getTweets().size());
    }

    @Test
    public void addTweetTest() throws Exception {
        User user = new User("Reginald");
        user.addTweet(new Tweet(user, "My authored tweet"));
        assertEquals(1, user.getTweets().size());
    }

    @Test
    public void addFollowerTest() throws Exception {
        User user = new User("Reginald");
        user.addFollower(new User("Jacob"));
        assertEquals(1, user.getFollowers().size());
        assertNotNull(user.getFollowers().get(0));
        assertNotEquals(user.getName(), user.getFollowers().get(0).getName());
    }

    @Test
    public void compareTo() throws Exception {
        List<User> unOrderdUsers = Arrays.asList(
                new User("Beyonce"),
                new User("Akon"),
                new User("Chris")
        );
        assertEquals("Beyonce", unOrderdUsers.get(0).getName());
        assertEquals("Akon", unOrderdUsers.get(1).getName());
        assertEquals("Chris", unOrderdUsers.get(2).getName());
        Collections.sort(unOrderdUsers);
        assertEquals("Akon", unOrderdUsers.get(0).getName());
        assertEquals("Beyonce", unOrderdUsers.get(1).getName());
        assertEquals("Chris", unOrderdUsers.get(2).getName());
    }

}