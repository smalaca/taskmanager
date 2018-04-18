package org.smalaca.taskmanager.service.sms;

import org.smalaca.taskmanager.domain.PhoneNumber;

public interface SmsCommunicator {
    void textTo(PhoneNumber phoneNumber, String message);
}
