package ch.bzz.roomManagement.data;

import ch.bzz.roomManagement.model.Room;
import ch.bzz.roomManagement.model.User;
import ch.bzz.roomManagement.service.Config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDao implements Dao<User, String> {
    public User getEntity(String username, String password) {
        User user = new User();

        String sqlQuery ="SELECT * FROM User WHERE userId=?";

        // TODO Tenzin: implementation überprüfen
        Map<Integer, String> values = new HashMap<>();
        values.put(1, username + password);

        try {
            ResultSet resultSet = MySqlDB.sqlSelect(sqlQuery, values);
            if (resultSet.next()) {
                setValues(resultSet, user);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            MySqlDB.sqlClose();
        }
        return user;
    }

    private void setValues(ResultSet resultSet, User user) throws SQLException {
        user.setUserId(resultSet.getInt("userId"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
    }
}
