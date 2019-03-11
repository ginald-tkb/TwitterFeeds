package com.company.Persistence;

import com.company.models.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by tshiamo on 2019/03/10.
 */
public class PersistenceDaoImplTest {
    private final static String USERS_FILE_PATH = "./src/test/resources/user.txt";
    private final static String TWEETS_FILE_PATH = "./src/test/resources/tweet.txt";

    @Test
    public void isInstanceOfIPersistance() {
        assertTrue(new PersistenceDaoImpl("", "") instanceof IPersistanceDao);
    }

    @Test
    public void findIndexTest() {
        List<User> users = Arrays.asList(
                new User("Beyonce"),
                new User("Akon"),
                new User("Chris")
        );
        assertEquals(1, PersistenceDaoImpl.findIndex(users, new User("Akon")));
        assertEquals(-1, PersistenceDaoImpl.findIndex(users, new User("Obama")));
        assertEquals(2, PersistenceDaoImpl.findIndex(users, new User("Chris")));
    }

    @Test
    public void getUsersTest() throws IOException {
        IPersistanceDao dao = new PersistenceDaoImpl(USERS_FILE_PATH, "");
        assertNotNull(dao.getUsers());
        assertNotEquals(0, dao.getUsers().size());
    }

    @Test
    public void getUsersIsFollowerSetTest() throws IOException {
        IPersistanceDao dao = new PersistenceDaoImpl(USERS_FILE_PATH, "");
        User user = dao.getUsers()
                .stream().filter(u -> u.getName().equals("Ward"))
                .findFirst().orElse(null);
        assertNotNull(user);
        assertNotNull(user.getFollowers());
        assertNotEquals(0, user.getFollowers().size());
        assertNotNull(user.getFollowers()
                .stream().filter(f -> f.getName().equals("Alan"))
                .findFirst().orElse(null));
    }

    @Test
    public void getUsersFollowerIsAddedToUsersTest() throws IOException {
        IPersistanceDao dao = new PersistenceDaoImpl(USERS_FILE_PATH, "");
        assertNotNull(dao.getUsers()
                .stream().filter(user -> user.getName().equals("Martin"))
                .findFirst().orElse(null));
    }

    @Test
    public void setTweetsTest() throws IOException {
        User beyonce = new User("Beyonce");

        beyonce.addFollower(new User("Mike"));

        IPersistanceDao dao = new PersistenceDaoImpl("", TWEETS_FILE_PATH);

        dao.setTweets(beyonce);
        assertEquals(2, beyonce.getTweets().size());
        assertEquals("Mike", beyonce.getTweets().get(0).getAuthor().getName());
        assertEquals("There are only two hard things.", beyonce.getTweets().get(0).getText());
        assertEquals("Beyonce", beyonce.getTweets().get(1).getAuthor().getName());
        assertEquals("Random numbers should not be generated.", beyonce.getTweets().get(1).getText());
    }

    @Test
    public void setTweetsOneFollowerTest() throws IOException {
        User akon = new User("Akon");

        akon.addFollower(new User("Phil"));

        IPersistanceDao dao = new PersistenceDaoImpl("", TWEETS_FILE_PATH);

        dao.setTweets(akon);
        assertEquals(1, akon.getTweets().size() );
        assertEquals("Phil", akon.getTweets().get(0).getAuthor().getName());
        assertEquals("If you have a procedure with 10 parameters.", akon.getTweets().get(0).getText());
    }

    @Test
    public void setTweetsOneOwnTweetTest() throws IOException {
        User chris = new User("Chris");

        IPersistanceDao dao = new PersistenceDaoImpl("", TWEETS_FILE_PATH);

        dao.setTweets(chris);
        assertEquals(1, chris.getTweets().size() );
        assertEquals("Chris", chris.getTweets().get(0).getAuthor().getName());
        assertEquals("I have no followers but my own tweet.", chris.getTweets().get(0).getText());
    }

    @Test
    public void setTweetsNoTweetTest() throws IOException {
        User noboday = new User("Nobody");

        IPersistanceDao dao = new PersistenceDaoImpl("", TWEETS_FILE_PATH);

        dao.setTweets(noboday);
        assertEquals(0, noboday.getTweets().size() );
    }

    @Test
    public void setTweetsNoTweetWithFollowersFTest() throws IOException {
        User noboday = new User("Nobody");
        noboday.addFollower(new User("Follower"));

        IPersistanceDao dao = new PersistenceDaoImpl("", TWEETS_FILE_PATH);

        dao.setTweets(noboday);
        assertEquals(0, noboday.getTweets().size() );
    }
}
