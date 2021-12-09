package ch.bzz.roomManagement.data;



import ch.bzz.roomManagement.model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data access for Room entity
 * <p>
 * M426: RoomManagement
 *
 * @author Skyelar Maurer
 * @version 1.0
 * @since 2021-12-02
 */
public class RoomDao implements Dao<Room, String> {
    /**
     * reads all rooms in the table "Room"
     *
     * @return list of room
     */
    @Override
    public List<Room> getAll() {
        List<Room> roomList = new ArrayList<>();
        String sqlQuery =
                "SELECT * FROM Room";
        try {
            ResultSet resultSet = MySqlDB.sqlSelect(sqlQuery);
            while (resultSet.next()) {
                Room room = new Room();
                setValues(resultSet, room);
                roomList.add(room);
            }
        } catch (SQLException sqlEx) {
            MySqlDB.printSQLException(sqlEx);
            throw new RuntimeException();
        } finally {

            MySqlDB.sqlClose();
        }
        return roomList;

    }

    /**
     * reads a room from the table "Room" identified by the roomId
     *
     * @param roomId the primary key
     * @return room object
     */
    @Override
    public Room getEntity(String roomId) {
        Room room = new Room();

        String sqlQuery =
                "SELECT * FROM Room WHERE roomId=?";
        Map<Integer, String> values = new HashMap<>();
        values.put(1, roomId);
        try {
            ResultSet resultSet = MySqlDB.sqlSelect(sqlQuery, values);
            if (resultSet.next()) {
                setValues(resultSet, room);
            }

        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            MySqlDB.sqlClose();
        }
        return room;

    }

    /**
     * sets the values of the attributes from the resultset
     *
     * @param resultSet the resultSet with an entity
     * @param room     a room object
     * @throws SQLException
     */
    private void setValues(ResultSet resultSet, Room room) throws SQLException {
        room.setRoomId(resultSet.getInt("roomId"));
        room.setName(resultSet.getString("name"));
        room.setDescription(resultSet.getString("description"));
        room.setType(resultSet.getString("type"));
        room.setPrice(resultSet.getFloat("price"));
        room.setArea(resultSet.getFloat("area"));
    }

}
