package Study.App.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import Study.App.model.enums.ParticipationRole;

@Entity
@Table(name="appuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private Set<ParticipationRole> roles = new HashSet<ParticipationRole>();

    private String name;
    private String username;
    private String password;
    
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
