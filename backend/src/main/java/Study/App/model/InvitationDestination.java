package Study.App.model;

import Study.App.model.enums.InvitationRole;
import Study.App.model.enums.InvitationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InvitationDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inviteeId;

    private InvitationRole role;

    private InvitationStatus status;

    public InvitationRole getRole() {
        return role;
    }

    public void setRole(InvitationRole role) {
        this.role = role;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public int getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(int inviteeId) {
        this.inviteeId = inviteeId;
    }

    
}
