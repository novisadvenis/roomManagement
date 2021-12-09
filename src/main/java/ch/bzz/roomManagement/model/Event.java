package ch.bzz.roomManagement.model;

import javax.ws.rs.FormParam;

/**
 * POJO for Event entity
 */
public class Event {

    @FormParam("eventId")
    private Integer eventId;

    @FormParam("title")
    private String title;

    @FormParam("description")
    private String description;

    @FormParam("organiser")
    private String organiser;


    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }
}
