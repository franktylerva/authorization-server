package com.example.authserver.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {

    @Id
    @Column(length=50)
    private String username;
    @Column(length=500)
    private String password;
    private boolean enabled;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Set<Role> authorities = new HashSet<>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Set<Group> groups = new HashSet<>();

    public void addRole(Role role) {
        role.addUser(this);
        this.authorities.add(role);
    }

    public void addGroup(Group group) {
        group.addUser(this);
        this.groups.add(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return username != null && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
