package model;

import dao.Identifiable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Gender gender;
    private Role role;
    private StatusReader status;
    private LocalDateTime profileCreated = LocalDateTime.now();
    private LocalDateTime profileModified = LocalDateTime.now();

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, String password, Gender gender, Role role, StatusReader status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.status = status;
    }

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(Long id, String firstName, String lastName, String email, String username, String password, Gender gender, Role role, StatusReader status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.profileCreated = profileCreated;
        this.profileModified = profileModified;
    }

    public User(String firstName, String lastName, String email, String username, String password, Gender gender, Role role, StatusReader status, LocalDateTime profileCreated, LocalDateTime profileModified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.profileCreated = profileCreated;
        this.profileModified = profileModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public StatusReader getStatus() {
        return status;
    }

    public void setStatus(StatusReader status) {
        this.status = status;
    }

    public LocalDateTime getProfileCreated() {
        return profileCreated;
    }

    public void setProfileCreated(LocalDateTime profileCreated) {
        this.profileCreated = profileCreated;
    }

    public LocalDateTime getProfileModified() {
        return profileModified;
    }

    public void setProfileModified(LocalDateTime profileModified) {
        this.profileModified = profileModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        return "User |" +
                "id= " + id +
                " | firstName= " + firstName +
                " | lastName= " + lastName +
                " | email= " + email +
                " | username= " + username +
                " | gender= " + gender +
                " | role= " + role +
                " | status= " + status +
                " | profileCreated= " + profileCreated +
                " | profileModified= " + profileModified +
                " |";
    }
}
