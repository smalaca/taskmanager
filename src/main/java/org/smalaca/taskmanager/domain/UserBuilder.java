package org.smalaca.taskmanager.domain;

import java.util.UUID;

public class UserBuilder {

    private String firstName;
    private String lastName;
    private String login;
    private String password;


    public User build() {
        return new User(firstName, lastName, login, password, IdentifierFactory.create());
    }

    public UserBuilder withName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withCredentials(String login, String password) {
        this.login = login;
        this.password = password;
        return this;
    }
}
