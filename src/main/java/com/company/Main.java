package com.company;

import com.company.Persistence.IPersistanceDao;
import com.company.Persistence.PersistenceDaoImpl;
import com.company.models.Tweet;
import com.company.models.User;

import java.io.IOException;

/**
 * Created by tshiamo on 2019/03/10.
 */
public class Main {
    private static final String USERS_FILE_PATH = "./src/main/resources/user.txt";
    private static final String TWEETS_FILE_PATH = "./src/main/resources/tweet.txt";

    public static void main(String[] args) {
        IPersistanceDao dao = new PersistenceDaoImpl(USERS_FILE_PATH, TWEETS_FILE_PATH);
        try {
            for (User user : dao.getUsers()) {
                dao.setTweets(user);
                System.out.println(buildOutput(user));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder buildOutput(User user) throws UnsupportedOperationException {
        if(null==user) throw new UnsupportedOperationException("Null User param not supported");
        StringBuilder consoleOutput = new StringBuilder(user.getName());
        for (Tweet tweet: user.getTweets()) {
            consoleOutput.append(String.format("\n\t@%s: %s", tweet.getAuthor().getName(), tweet.getText()));
        }
        return consoleOutput;
    }
}
