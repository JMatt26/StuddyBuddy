package Study.App.model;

import Study.App.model.enums.ParticipationRole;

public class Participants {
    private ParticipationRole role;

    private boolean isGoing = false;

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

    
}
