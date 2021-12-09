package ch.bzz.roomManagement.data;



import ch.bzz.roomManagement.model.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data access for event entity
 * <p>
 * M426: RoomManagement
 *
 * @author Skyelar Maurer
 * @version 1.0
 * @since 2021-12-02
 */
public class EventDao implements Dao<Event, String> {
    /**
     * reads all events in the table "Event"
     *
     * @return list of events
     */
    @Override
    public List<Event> getAll() {
        List<Event> eventList = new ArrayList<>();
        String sqlQuery =
                "SELECT * FROM Event";
        try {
            ResultSet resultSet = MySqlDB.sqlSelect(sqlQuery);
            while (resultSet.next()) {
                Event event = new Event();
                setValues(resultSet, event);
                eventList.add(event);
            }
        } catch (SQLException sqlEx) {
            MySqlDB.printSQLException(sqlEx);
            throw new RuntimeException();
        } finally {

            MySqlDB.sqlClose();
        }
        return eventList;

    }

    /**
     * reads an event from the table "Event" identified by the eventId
     *
     * @param eventId the primary key
     * @return event object
     */
    @Override
    public Event getEntity(String eventId) {
        Event event = new Event();

        String sqlQuery =
                "SELECT * FROM Event WHERE eventId=?";
        Map<Integer, String> values = new HashMap<>();
        values.put(1, eventId);
        try {
            ResultSet resultSet = MySqlDB.sqlSelect(sqlQuery, values);
            if (resultSet.next()) {
                setValues(resultSet, event);
            }

        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            MySqlDB.sqlClose();
        }
        return event;

    }

    /**
     * sets the values of the attributes from the resultset
     *
     * @param resultSet the resultSet with an entity
     * @param event     an event object
     * @throws SQLException
     */
    private void setValues(ResultSet resultSet, Event event) throws SQLException {
        event.setEventId(resultSet.getInt("eventId"));
        event.setTitle(resultSet.getString("title"));
        event.setDescription(resultSet.getString("description"));
        event.setOrganiser(resultSet.getString("organiser"));
    }

}
