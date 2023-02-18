package Study.App.model;

import java.util.Set;

import Study.App.model.enums.ParticipationRole;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participationID;

    private ParticipationRole role;

    @ManyToOne
    private Session session;

    @ManyToOne
    private UserInformation userInformation;
    
    private Boolean isGoing = false;
    
    public ParticipationRole getRole() {
        return role;
    }
    
    public void setRole(ParticipationRole role) {
        this.role = role;
    }
    
    public boolean isGoing() {
        return isGoing;
    }
    
    public void setGoing(boolean isGoing) {
        this.isGoing = isGoing;
    }
    
    public int getParticipationID() {
        return participationID;
    }
    
    public void setParticipationId(int participationID) {
        this.participationID = participationID;
    }
    
    public Session getSession() {
        return session;
    }
    
    public void setSession(Session session) {
        this.session = session;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }
    
}
