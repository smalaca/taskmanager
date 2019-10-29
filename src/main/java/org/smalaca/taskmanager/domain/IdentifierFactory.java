package org.smalaca.taskmanager.domain;

import java.util.UUID;

public class IdentifierFactory {
    public static String create() {
        return UUID.randomUUID().toString();
    }
}
