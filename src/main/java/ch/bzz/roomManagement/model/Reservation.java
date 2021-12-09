package ch.bzz.roomManagement.model;

import javax.ws.rs.FormParam;
import java.sql.Date;

/**
 * POJO for Reservation entity
 */
public class Reservation {

    @FormParam("reservationId")
    private Integer reservationId;

    @FormParam("start")
    private Date start;

    @FormParam("end")
    private Date end;

    private Room room;
    private Event event;


    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
