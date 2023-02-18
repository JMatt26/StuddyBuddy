package Study.App.model;

import Study.App.model.enums.ParticipationRole;
import jakarta.persistence.Entity;
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

    private Boolean isGoing = false;

    @ManyToOne
    private Session session;

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

}
