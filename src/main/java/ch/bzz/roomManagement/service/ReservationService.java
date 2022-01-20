package ch.bzz.roomManagement.service;

import ch.bzz.roomManagement.data.Dao;
import ch.bzz.roomManagement.data.ReservationDao;
import ch.bzz.roomManagement.model.Event;
import ch.bzz.roomManagement.model.Reservation;
import ch.bzz.roomManagement.model.Room;
import ch.bzz.roomManagement.util.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * service controller for reservations
 * <p>
 * M426: RoomManagement
 *
 * @author Skyelar Maurer
 * @since 2021-12-02
 */
@Path("reservation")
public class ReservationService {

    /**
     * produces a list of all reservations
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listReservations() {

        int httpStatus = 200;
        Dao<Reservation, String> reservationDao = new ReservationDao();
        List<Reservation> reservationList = reservationDao.getAll();
        if (reservationList.isEmpty())
            httpStatus = 404;

        return Response
                .status(httpStatus)
                .entity(reservationList)
                .build();
    }

    /**
     * reads a single reservation identified by the reservationId
     *
     * @param reservationId the reservationId in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readReservation(
            @QueryParam("reservationId") String reservationId
    ) {
        int httpStatus = 200;
        Dao<Reservation, String> reservationDao = new ReservationDao();
        Reservation reservation = reservationDao.getEntity(reservationId);
        if (reservation.getReservationId() == null)
            httpStatus = 404;

        return Response
                .status(httpStatus)
                .entity(reservation)
                .build();
    }

    @POST
    @Path("save")
    @Produces(MediaType.TEXT_PLAIN)
    public Response saveReservation(
            @QueryParam("reservationId") int reservationId,
            @BeanParam Reservation reservation,
            @BeanParam Room room,
            @BeanParam Event event
    ) {
        int httpStatus;
        String message;
        reservation.setReservationId(reservationId);
        reservation.setEvent(event);
        reservation.setRoom(room);
        Dao<Reservation, String> reservationDao = new ReservationDao();
        Result result = reservationDao.save(reservation);
        if (result == Result.SUCCESS) {
            message = "Reservation gespeichert";
            httpStatus = 200;
        } else {
            message = "Fehler beim Speichern der Reservation";
            httpStatus = 500;
        }
        return Response
                .status(httpStatus)
                .entity(message)
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteReservation(
            @QueryParam("reservationId") String reservationId
    ) {
        int httpStatus = 200;
        Dao<Reservation, String> reservationDao = new ReservationDao();
        Result result = reservationDao.delete(reservationId);
        if (result != Result.SUCCESS) httpStatus = 500;
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @PATCH
    @Path("patch")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateReservation() {
        //todo patch api
        return null;
    }

}
