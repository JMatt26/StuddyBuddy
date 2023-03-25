package Study.App.controller.TOs;

import java.util.List;
import java.util.Set;

public class SessionInformationTO {
    public Integer sessionInformationId;
    public String startTime;
    public String endTime;
    public List<String> courses;
    public Boolean isOnline;
    public List<String> materialUrl;
    public Integer sessionId;
    public Integer locationId;
    public List<String> tags;

    public SessionInformationTO(Integer sessionInformationId, String startTime, String endTime, List<String> courses, Boolean isOnline, List<String> materialUrl, Integer sessionId, Integer locationId, List<String> tags) {
        this.sessionInformationId = sessionInformationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courses = courses;
        this.isOnline = isOnline;
        this.materialUrl = materialUrl;
        this.sessionId = sessionId;
        this.locationId = locationId;
        this.tags = tags;
    }

    public SessionInformationTO() {
    }

    @Override
    public String toString() {
        return "SessionInformationTO [courses=" + courses + ", endTime=" + endTime + ", isOnline=" + isOnline
                + ", locationId=" + locationId + ", materialUrl=" + materialUrl + ", sessionId=" + sessionId
                + ", sessionInformationId=" + sessionInformationId + ", startTime=" + startTime + ", tags=" + tags + "]";
    }
}
