package com.hcc.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
   private long id;
    private LocalDate cohortStartDate;
    private String username;
    private List<Authority> authorities;
    private String password;

    public User(LocalDate cohortStartDate, String username, List<Authority> authorities, String password) {
        this.cohortStartDate = cohortStartDate;
        this.username = username;
        this.authorities = authorities;
        this.password = password;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCohortStartDate() {
        return cohortStartDate;
    }

    public void setCohortStartDate(LocalDate cohortStartDate) {
        this.cohortStartDate = cohortStartDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(cohortStartDate, user.cohortStartDate) && Objects.equals(username, user.username) && Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cohortStartDate, username, authorities);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cohortStartDate=" + cohortStartDate +
                ", username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
