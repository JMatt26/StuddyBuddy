package Study.App.controller.TOs;

import java.util.Set;

public class SessionTO {
    public Integer sessionId;
    public Boolean isPrivate;
    public String title;
    public Integer capacity;
    public String description;
    public Integer numberOfAttendees;
    public Set<Integer> participationIds;
    public Integer sessionInformationId;

    public SessionTO(Integer sessionId, Boolean isPrivate, String title, Integer capacity, String description, Integer numberOfAttendees, Set<Integer> participationIds, Integer sessionInformationId) {
        this.sessionId = sessionId;
        this.isPrivate = isPrivate;
        this.title = title;
        this.capacity = capacity;
        this.description = description;
        this.numberOfAttendees = numberOfAttendees;
        this.participationIds = participationIds;
        this.sessionInformationId = sessionInformationId;
    }

    public SessionTO() {
    }

    @Override
    public String toString() {
        return "SessionTO [capacity=" + capacity + ", description=" + description + ", isPrivate=" + isPrivate
                + ", numberOfAttendees=" + numberOfAttendees + ", participationIds=" + participationIds
                + ", sessionInformationId=" + sessionInformationId + ", sessionId=" + sessionId + ", title=" + title
                + "]";
    }
}
