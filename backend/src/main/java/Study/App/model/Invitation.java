package Study.App.model;

import Study.App.model.enums.InvitationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Invitation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inviteId;

    private InvitationType invitationType;

    @ManyToOne
    private InvitationDestination invitationDestination;

    @ManyToOne
    private Session session;

    public InvitationDestination getInvitee() {
        return invitationDestination;
    }

    public void setInvitee(InvitationDestination invitee) {
        this.invitationDestination = invitee;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getInviteId() {
        return inviteId;
    }

    public void setInviteId(int inviteId) {
        this.inviteId = inviteId;
    }

    public InvitationType getInvitationType() {
        return invitationType;
    }

    public void setInvitationType(InvitationType invitationType) {
        this.invitationType = invitationType;
    }

    
}
