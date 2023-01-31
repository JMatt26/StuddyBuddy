package JMatt26.Study.App.model;

import JMatt26.Study.App.model.enums.InvitationRole;
import JMatt26.Study.App.model.enums.InvitationStatus;

public class Invitees {
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

    
}
