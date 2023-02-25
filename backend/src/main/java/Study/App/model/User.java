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
import jakarta.persistence.OneToOne;
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
    
    private Set<ParticipationRole> roles = new HashSet<ParticipationRole>();
    
    private String name;
    @Column (unique = true)
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

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password + "]";
    }
}
