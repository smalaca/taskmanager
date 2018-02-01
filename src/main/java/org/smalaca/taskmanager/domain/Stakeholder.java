package org.smalaca.taskmanager.domain;

public class Stakeholder {
    private String userName;
    private PhoneNumber phoneNumber;
    private EmailAddress emailAddress;

    public String getUserName() {
        return userName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }
}
