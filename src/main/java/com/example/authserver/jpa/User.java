package com.example.authserver.jpa;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(length=50)
    private String username;
    @Column(length=500)
    private String password;
    private boolean enabled;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Role> authorities = new HashSet<>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Group> groups = new HashSet<>();

    public void addRole(Role role) {
        role.addUser(this);
        this.authorities.add(role);
    }

    public void addGroup(Group group) {
        group.addUser(this);
        this.groups.add(group);
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
