package Study.App.controller.TOs;

import java.util.List;

public class SessionInformationTO {
    public Integer sessionInformationId;
    public String startTime;
    public String endTime;
    public String course;
    public Boolean isOnline;
    public List<String> materialUrl;
    public Integer sessionId;
    public Integer locationId;

    public SessionInformationTO(Integer sessionInformationId, String startTime, String endTime, String course, Boolean isOnline, List<String> materialUrl, Integer sessionId, Integer locationId) {
        this.sessionInformationId = sessionInformationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.course = course;
        this.isOnline = isOnline;
        this.materialUrl = materialUrl;
        this.sessionId = sessionId;
        this.locationId = locationId;
    }

    public SessionInformationTO() {
    }

    @Override
    public String toString() {
        return "SessionInformationTO [course=" + course + ", endTime=" + endTime + ", isOnline=" + isOnline + ", locationId=" + locationId + ", materialUrl=" + materialUrl + ", sessionId=" + sessionId + ", sessionInformationId=" + sessionInformationId + ", startTime=" + startTime + "]";
    }
}
