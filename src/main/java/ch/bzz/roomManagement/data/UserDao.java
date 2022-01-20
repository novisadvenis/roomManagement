package ch.bzz.roomManagement.data;

import ch.bzz.roomManagement.model.Room;
import ch.bzz.roomManagement.model.User;
import ch.bzz.roomManagement.service.Config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDao implements Dao<User,String> {
    public User getEntity(String username, String password) {
        //todo
        //need password hashing and only userId parameter is not enough
        return null;
    }

    private void setValues(ResultSet resultSet, User user) throws SQLException {
        user.setUserId(resultSet.getInt("userId"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        // todo recheck attributes
    }
}
