package Study.App.controller.TOs;

public class SessionTORequest {
    public Integer sessionId;
    public Boolean ispublic;
    public String title;
    public Integer capacity;
    public String description;
    public Integer attendees;
    public Integer participantId;
    public Integer sessionInformationId;

    public SessionTORequest(Integer sessionId, Boolean ispublic, String title, Integer capacity, String description, Integer attendees, Integer participantId, Integer sessionInformationId) {
        this.sessionId = sessionId;
        this.ispublic = ispublic;
        this.title = title;
        this.capacity = capacity;
        this.description = description;
        this.attendees = attendees;
        this.participantId = participantId;
        this.sessionInformationId = sessionInformationId;
    }

    public SessionTORequest() {
    }
}
