package Study.App.controller.TOs;

public class SessionInformationTO {
    public Integer sessionInformationId;
    public String startTime;
    public String endTime;
    public String course;
    public Boolean isOnline;
    public String materialUrl;
    public Integer sessionId;
    public Integer educationId;
    public Integer locationId;

    public SessionInformationTO(Integer sessionInformationId, String startTime, String endTime, String course, Boolean isOnline, String materialUrl, Integer sessionId, Integer educationId, Integer locationId) {
        this.sessionInformationId = sessionInformationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
        this.isOnline = isOnline;
        this.materialUrl = materialUrl;
        this.sessionId = sessionId;
        this.educationId = educationId;
        this.locationId = locationId;
    }

    public SessionInformationTO() {
    }
}
