package ch.bzz.roomManagement.model;

import javax.ws.rs.FormParam;

/**
 * POJO for the Entity Room
 */
public class Room {

    @FormParam("roomId")
    private Integer roomId;

    @FormParam("name")
    private String name;

    @FormParam("description")
    private String description;

    @FormParam("type")
    private String type;

    @FormParam("price")
    private String price;

    @FormParam("area")
    private String area;


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
