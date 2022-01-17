package ch.bzz.roomManagement.data;

import ch.bzz.roomManagement.model.Reservation;
import ch.bzz.roomManagement.model.Room;
import ch.bzz.roomManagement.util.ReservationIdGenerator;
import ch.bzz.roomManagement.util.Result;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data access for reservation entity
 * <p>
 * M426: RoomManagement
 *
 * @author Skyelar Maurer
 * @version 1.0
 * @since 2021-12-02
 */
public class ReservationDao implements Dao<Reservation, String> {
    /**
     * reads all reservations in the table "Reservation"
     *
     * @return list of reservations
     */
    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservationList = new ArrayList<>();
        String sqlQuery =
                "SELECT * FROM Reservation";
        try {
            ResultSet resultSet = MySqlDB.sqlSelect(sqlQuery);
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                setValues(resultSet, reservation);
                reservationList.add(reservation);
            }
        } catch (SQLException sqlEx) {
            MySqlDB.printSQLException(sqlEx);
            throw new RuntimeException();
        } finally {

            MySqlDB.sqlClose();
        }
        return reservationList;

    }

    /**
     * reads a reservation from the table "Reservation" identified by the reservationId
     *
     * @param reservationId the primary key
     * @return reservation object
     */
    @Override
    public Reservation getEntity(String reservationId) {
        Reservation reservation = new Reservation();

        String sqlQuery =
                "SELECT * FROM Reservation WHERE reservationId=?";
        Map<Integer, String> values = new HashMap<>();
        values.put(1, reservationId);
        try {
            ResultSet resultSet = MySqlDB.sqlSelect(sqlQuery, values);
            if (resultSet.next()) {
                setValues(resultSet, reservation);
            }

        } catch (SQLException sqlEx) {

            sqlEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            MySqlDB.sqlClose();
        }
        return reservation;

    }

    /**
     * saves a reservation in the table "Reservation"
     *
     * @param reservation the reservation object
     * @return Result code
     */
    @Override
    public Result save(Reservation reservation) {
        Map<Integer, String> values = new HashMap<>();
        String sqlQuery;
        if (reservation.getReservationId() == null) {
            reservation.setReservationId(ReservationIdGenerator.generateRandomId());
            sqlQuery = "INSERT INTO Reservation";
        } else {
            sqlQuery = "REPLACE Reservation";
        }
        sqlQuery += " SET reservationId=?," +
                " start=?," +
                " end=?," +
                " Room_roomId=?," +
                " Event_eventId=?";

        values.put(1, reservation.getReservationId().toString());
        values.put(2, reservation.getStart().toString());
        values.put(3, reservation.getEnd().toString());
        values.put(4, reservation.getRoom().getRoomId().toString());
        values.put(5, reservation.getEvent().getEventId().toString());

        try {
            return MySqlDB.sqlUpdate(sqlQuery, values);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Result update(Reservation reservation) {
        Map<Integer, String> values = new HashMap<>();
        String sqlQuery;
        sqlQuery = "UPDATE Reservation";
        sqlQuery += " SET  start=?," +
                " end=?," +
                " Room_roomId=?," +
                " Event_eventId=?";
        sqlQuery += " WHERE reservationId=?";

        values.put(1, reservation.getStart().toString());
        values.put(2, reservation.getEnd().toString());
        values.put(3, reservation.getRoom().getRoomId().toString());
        values.put(4, reservation.getEvent().getEventId().toString());
        values.put(5, reservation.getReservationId().toString());

        try {
            return MySqlDB.sqlUpdate(sqlQuery, values);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * deletes a reservation in the table "Reservation" identified by the reservationId
     *
     * @param reservationId the primary key
     * @return Result code
     */
    @Override
    public Result delete(String reservationId) {
        String sqlQuery =
                "DELETE FROM Reservation" +
                        " WHERE reservationId=?";
        Map<Integer, String> values = new HashMap<>();
        values.put(1, reservationId);

        try {
            return MySqlDB.sqlUpdate(sqlQuery, values);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * sets the values of the attributes from the resultset
     *
     * @param resultSet the resultSet with an entity
     * @param reservation      a reservation object
     * @throws SQLException
     */
    private void setValues(ResultSet resultSet, Reservation reservation) throws SQLException {
        EventDao eventDao = new EventDao();
        RoomDao roomDao = new RoomDao();

        reservation.setEnd(resultSet.getDate("end"));
        reservation.setStart(resultSet.getDate("start"));
        reservation.setReservationId(resultSet.getInt("reservationId"));
        reservation.setRoom(roomDao.getEntity(resultSet.getString("Room_roomId")));
        reservation.setEvent(eventDao.getEntity(resultSet.getString("Event_eventId")));
    }

}
