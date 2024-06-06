package com.dolphin.repositoryTesting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;
    private String username;
    private LocalDate dateOfBirth;
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Result> results = new HashSet<>();

    public User() {}

    public User(String id, String username, LocalDate dateOfBirth, Role role) {
        this.id = id;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public User(String username, LocalDate dateOfBirth, Role role) {
        this.id = generateId();
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getAge() {
        return calculateAge();
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    private static String generateId() {
        return UUID.randomUUID().toString();
    }

    private int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }
}
