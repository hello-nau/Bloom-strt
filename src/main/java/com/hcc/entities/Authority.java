package com.hcc.entities;

import com.hcc.enums.AuthorityEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private AuthorityEnum authority;
    @ManyToOne
    private User user;

    public Authority(AuthorityEnum authority, User user) {
        this.authority = authority;
        this.user = user;
    }
    public Authority(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority.toString();
    }

    public void setAuthority(AuthorityEnum authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority1 = (Authority) o;
        return id == authority1.id && Objects.equals(authority, authority1.authority) && Objects.equals(user, authority1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority, user);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", user=" + user +
                '}';
    }
}
