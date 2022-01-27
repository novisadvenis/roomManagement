package ch.bzz.roomManagement.service;

import ch.bzz.roomManagement.data.Dao;
import ch.bzz.roomManagement.data.RoomDao;
import ch.bzz.roomManagement.data.UserDao;
import ch.bzz.roomManagement.model.Room;
import ch.bzz.roomManagement.model.User;
import ch.bzz.roomManagement.util.TokenHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("user")
public class UserService {

    @POST
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus = 200;
        Map<String,String> sessionCookie = new HashMap<>();
        Dao<User, String> userDao = new UserDao();
        User user = userDao.getEntity(username, password);
        if (user.getUserId() == null) {
            httpStatus = 404;
        } else {
           //todo session cookie build with attributes
            sessionCookie.put("userName", user.getUsername());
            sessionCookie.put("admin", "true");

        }

        NewCookie tokenCookie = new TokenHandler().buildCookie(sessionCookie);
        return Response
                .status(httpStatus)
                .entity(user)
                .cookie(tokenCookie)
                .build();
    }
}
