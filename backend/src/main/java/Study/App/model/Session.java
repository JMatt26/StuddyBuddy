package Study.App.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Participation> participations;

    @OneToOne (cascade = CascadeType.REMOVE)
    private SessionInformation sessionInformation;

    public Set<Participation> getParticipants() {
        return participations;
    }
    public void setParticipants(Set<Participation> participations) {
        this.participations = participations;
    }
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
    public void setPrivate(boolean isPrivate) {
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
    
}
