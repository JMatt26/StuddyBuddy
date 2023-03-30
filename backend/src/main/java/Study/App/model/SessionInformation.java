package Study.App.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class SessionInformation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionInformationId;

    private Date startTime;

    private Date endTime;

    private List<String> courses;

    private List<String> tags;

    private boolean isOnline = false;

    private List<String> materialUrl;

    @OneToOne
    private Session session;
    
    @ManyToOne
    private Education education;
    
    @ManyToOne
    private Location location;

    private String adminUsername;
    
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Education getEducation() {
        return education;
    }
    
    public void setEducation(Education education) {
        this.education = education;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
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
    
    public List<String> getCourses() {
        return courses;
    }
    
    public void setCourses(List<String> courses) {
        this.courses = courses;
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
    
    public Session getSession() {
        return session;  
    }
    
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "SessionInformation [courses=" + courses + ", endTime=" + endTime + ", isOnline=" + isOnline
                + ", materialUrl=" + materialUrl + ", session=" + session + ", sessionInformationId="
                + sessionInformationId + ", startTime=" + startTime +  ", tags=" + tags +"]";
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
    
}
