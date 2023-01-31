package JMatt26.Study.App.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SessionInformation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionInformationId;

    private Date startTime;

    private Date endTime;

    private String course;

    private boolean isOnline = false;

    private List<String> materialUrl;

    public int getSessionInformationId() {
        return sessionInformationId;
    }

    public void setSessionInformationId(int sessionInformationId) {
        this.sessionInformationId = sessionInformationId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public List<String> getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(List<String> materialUrl) {
        this.materialUrl = materialUrl;
    }

    
}
