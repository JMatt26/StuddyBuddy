package Study.App.model;

import jakarta.persistence.Column;
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

import java.util.HashSet;
import java.util.Set;

import Study.App.model.enums.ParticipationRole;

@Entity
@Table(name="AppUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    // 
    private Set<ParticipationRole> roles = new HashSet<ParticipationRole>();

    private String name;
    @Column (unique = true)
    private String username;
    private String password;
    
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinColumn (name = "friends")
    private Set<User> friends = new HashSet<User>();

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinColumn (name = "participants")
    private Set<Participant> participants = new HashSet<Participant>();

    @ManyToOne 
    private Invitee invitee;

    public Set<User> getFriends() {
        return friends;
    }
    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
    public Set<Participant> getParticipants() {
        return participants;
    }
    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }
    public Invitee getInvitee() {
        return invitee;
    }
    public void setInvitee(Invitee invitee) {
        this.invitee = invitee;
    }
    public Set<ParticipationRole> getRoles() {
        return roles;
    }
    public void setRoles(Set<ParticipationRole> roles) {
        this.roles = roles;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
