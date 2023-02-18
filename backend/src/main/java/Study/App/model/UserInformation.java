package Study.App.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "AppUserInformation")
public class UserInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "friends")
    private Set<UserInformation> friends = new HashSet<UserInformation>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "participation")
    private Set<Participation> participations = new HashSet<Participation>();

    @ManyToOne
    private InvitationDestination invitationDestination;

    public Set<UserInformation> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserInformation> friends) {
        this.friends = friends;
    }

    public Set<Participation> getParticipation() {
        return participations;
    }

    public void setParticipation(Set<Participation> participations) {
        this.participations = participations;
    }

    public InvitationDestination getInvitee() {
        return invitationDestination;
    }

    public void setInvitee(InvitationDestination invitationDestination) {
        this.invitationDestination = invitationDestination;
    }
}
