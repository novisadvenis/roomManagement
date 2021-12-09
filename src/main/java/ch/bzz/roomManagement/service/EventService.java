package ch.bzz.roomManagement.service;

import ch.bzz.roomManagement.data.Dao;
import ch.bzz.roomManagement.data.EventDao;
import ch.bzz.roomManagement.model.Event;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * service controller for events
 * <p>
 * M426: RoomManagement
 *
 * @author Skyelar Maurer
 * @since 2021-12-02
 */
@Path("event")
public class EventService {

    /**
     * produces a list of all events
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEvents() {

        int httpStatus = 200;
        Dao<Event, String> eventDao = new EventDao();
        List<Event> eventList = eventDao.getAll();
        if (eventList.isEmpty())
            httpStatus = 404;

        return Response
                .status(httpStatus)
                .entity(eventList)
                .build();
    }

    /**
     * reads a single event identified by the eventId
     *
     * @param eventId the eventId in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEvent(
            @QueryParam("eventId") String eventId
    ) {
        int httpStatus = 200;
        Dao<Event, String> eventDao = new EventDao();
        Event event = eventDao.getEntity(eventId);
        if (event.getEventId() == null)
            httpStatus = 404;

        return Response
                .status(httpStatus)
                .entity(event)
                .build();
    }
}
