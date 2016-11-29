package org.smalaca.taskmanager.domain;

public class ProductOwner {
    private EmailAddress emailAddress;
    private PhoneNumber phoneNumber;
    private String userName;

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }
}
