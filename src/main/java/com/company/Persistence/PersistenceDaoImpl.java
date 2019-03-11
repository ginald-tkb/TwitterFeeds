package com.company.Persistence;

import com.company.models.Tweet;
import com.company.models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by tshiamo on 2019/03/09.
 */
public class PersistenceDaoImpl implements IPersistanceDao {
    private final String FOLLOWS_STR = "follows";
    private final String usersFilePath;
    private final String tweetsFilePath;

    public PersistenceDaoImpl(String usersFilePath, String tweetsFilePath) {
        this.usersFilePath = usersFilePath;
        this.tweetsFilePath = tweetsFilePath;
    }

    @Override
    public List<User> getUsers() throws IOException {
        List<User> users = new ArrayList<>();
        FileReader fileReader = new FileReader(usersFilePath);

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                int followsIndex = line.indexOf(FOLLOWS_STR);
                User user = new User(line.substring(0, followsIndex).trim());
                String[] followers = line.substring(followsIndex + FOLLOWS_STR.length()).split(",");

                if(-1==findIndex(users, user)) users.add(user);

                for (String followerStr: followers) {
                    User follower = new User(followerStr.trim());
                    users.get(findIndex(users, user))
                            .addFollower(follower);
                    if(-1==findIndex(users, follower)) users.add(follower);
                }
            }
        }

        Collections.sort(users);
        return users;
    }

    public static int findIndex(List<User> users, User user) {
        return IntStream.range(0, users.size())
                .filter(i -> user.getName().equals(users.get(i).getName()))
                .findFirst().orElse(-1);
    }

    @Override
    public void setTweets(User user) throws IOException {
        FileReader fileReader = new FileReader(tweetsFilePath);

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] tweetsStr = line.split(">");
                User author = new User(tweetsStr[0]);

                if(-1!=findIndex(user.getFollowers(), author) || user.getName().equals(author.getName())) {
                    user.addTweet(new Tweet(author, tweetsStr[1].trim()));
                }
            }
        }
    }
}
