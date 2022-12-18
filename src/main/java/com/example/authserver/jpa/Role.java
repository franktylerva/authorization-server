package com.example.authserver.jpa;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class Role {

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    @Id
    private String name;
    @ManyToMany
    private Set<User> users = new HashSet<>();

    @ManyToMany
    private Set<Group> groups = new HashSet<>();

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role authority = (Role) o;
        return Objects.equals(name, authority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
