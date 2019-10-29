package org.smalaca.taskmanager.domain;

import org.smalaca.taskmanager.dto.UserDto;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private PhoneNumber phoneNumber;
    private EmailAddress emailAddress;
    private TeamRole teamRole;

    public User(String firstName, String lastName, String login, String password, String id) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public User() {

    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User copy() {
        User copy = new User();
        copy.id = id;
        copy.firstName = firstName;
        copy.lastName = lastName;
        copy.login = login;
        copy.password = password;
        return copy;
    }

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public UserDto mapUserToDTO() {
        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setLogin(login);
        userDto.setPassword(password);
        userDto.setId(id);
        return userDto;
    }
}
