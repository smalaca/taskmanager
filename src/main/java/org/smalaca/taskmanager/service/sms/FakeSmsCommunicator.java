package org.smalaca.taskmanager.service.sms;

import org.smalaca.taskmanager.domain.PhoneNumber;

public class FakeSmsCommunicator implements SmsCommunicator {
    @Override
    public void textTo(PhoneNumber phoneNumber, String message) {

    }
}
