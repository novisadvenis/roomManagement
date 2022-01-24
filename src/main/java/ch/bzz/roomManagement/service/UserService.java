package ch.bzz.roomManagement.service;

import ch.bzz.roomManagement.data.Dao;
import ch.bzz.roomManagement.data.RoomDao;
import ch.bzz.roomManagement.data.UserDao;
import ch.bzz.roomManagement.model.Room;
import ch.bzz.roomManagement.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("user")
public class UserService {

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUser(
            @QueryParam("username") String username,
            @QueryParam("password") String password
    ) {
        int httpStatus = 200;
        Dao<User, String> userDao = new UserDao();
        User user = userDao.getEntity(username, password);
        if (user.getUserId() == null)
            httpStatus = 404;

        return Response
                .status(httpStatus)
                .entity(user)
                .build();
    }
}
