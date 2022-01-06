package ch.bzz.roomManagement.service;


import ch.bzz.roomManagement.data.Dao;
import ch.bzz.roomManagement.data.RoomDao;
import ch.bzz.roomManagement.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * service controller for rooms
 * <p>
 * M426: RoomManagement
 *
 * @author Skyelar Maurer
 * @since 2021-12-02
 */
@Path("room")
public class RoomService {

    /**
     * produces a list of all rooms
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRooms() {

        int httpStatus = 200;
        Dao<Room, String> roomDao = new RoomDao();
        List<Room> roomList = roomDao.getAll();
        if (roomList.isEmpty())
            httpStatus = 404;

        return Response
                .status(httpStatus)
                .entity(roomList)
                .build();
    }

    @GET
    @Path("list/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRooms(@PathParam("type") String type) {

        int httpStatus = 200;
        Dao<Room, String> roomDao = new RoomDao();
        List<Room> roomList = roomDao.getAll(type);
        if (roomList.isEmpty())
            httpStatus = 404;

        return Response
                .status(httpStatus)
                .entity(roomList)
                .build();
    }

    /**
     * reads a single room identified by the roomId
     *
     * @param roomId the roomId in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readRoom(
            @QueryParam("roomId") String roomId
    ) {
        int httpStatus = 200;
        Dao<Room, String> roomDao = new RoomDao();
        Room room = roomDao.getEntity(roomId);
        if (room.getRoomId() == null)
            httpStatus = 404;

        return Response
                .status(httpStatus)
                .entity(room)
                .build();
    }
}
