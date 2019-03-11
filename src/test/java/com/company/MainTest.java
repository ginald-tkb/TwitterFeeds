package com.company;

import com.company.models.Tweet;
import com.company.models.User;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by tshiamo on 2019/03/11.
 */
public class MainTest {
    @Test
    public void buildOutputTest() {
        User user = new User("Tshiamo");
        user.addTweet(new Tweet(new User("Reginald"),"Followers authored Tweet"));
        user.addTweet(new Tweet(new User("Tshiamo"),"User's own Tweet"));
        StringBuilder consoleOutput = Main.buildOutput(user);
        String expectedStr = "Tshiamo"
            + "\n\t@Reginald: Followers authored Tweet"
            + "\n\t@Tshiamo: User's own Tweet";
        assertNotNull(consoleOutput);
        assertEquals(expectedStr, consoleOutput.toString());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void buildOutputNullUserTest() {
        Main.buildOutput(null);
    }

    @Test
    public void buildOutputNoTweetsTest() {
        User user = new User("Tshiamo");
        assertEquals("Tshiamo", Main.buildOutput(user).toString());
    }
}
