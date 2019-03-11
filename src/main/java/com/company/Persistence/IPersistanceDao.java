package com.company.Persistence;

import com.company.models.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by tshiamo on 2019/03/09.
 */
public interface IPersistanceDao {
    List<User> getUsers() throws IOException;
    void setTweets(User user) throws IOException;
}
