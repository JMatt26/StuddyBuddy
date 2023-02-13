package Study.App.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="AppUserInformation")
public class UserInformation {
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinColumn (name = "friends")
    private Set<User> friends = new HashSet<User>();
    
    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn (name = "participation")
    private Set<Participation> participations = new HashSet<Participation>();
    
    @ManyToOne 
    private InvitationDestination invitationDestination;
    
    public Set<User> getFriends() {
        return friends;
    }
    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
    public Set<Participation> getParticipants() {
        return participations;
    }
    public void setParticipants(Set<Participation> participations) {
        this.participations = participations;
    }
    public InvitationDestination getInvitee() {
        return invitationDestination;
    }
    public void setInvitee(InvitationDestination invitationDestination) {
        this.invitationDestination = invitationDestination;
    }
}
