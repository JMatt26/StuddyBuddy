package Study.App.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;

    private Boolean isPrivate = false;
    private String title;
    private Integer capacity;
    private String description;

    @OneToMany(mappedBy = "session", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Participation> participations;
    
    @OneToOne (cascade = CascadeType.REMOVE)
    private SessionInformation sessionInformation;
    
    public SessionInformation getSessionInformation() {
        return sessionInformation;
    }
    public void setSessionInformation(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }
    public int getSessionId() {
        return sessionId;
    }
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    public boolean isPrivate() {
        return isPrivate;
    }
    public void setPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Participation> getParticipations() {
        return participations;
    }
    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    @Override
    public String toString() {
        return "Session [sessionId=" + sessionId + ", isPrivate=" + isPrivate + ", title=" + title + ", capacity="
        + capacity + ", description=" + description + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session other = (Session) obj;
        
        if (sessionId == null) {
            if (other.sessionId != null)
                return false;
        }
        if (!sessionId.equals(other.sessionId))
            return false;
        return true;
    }
    
}
