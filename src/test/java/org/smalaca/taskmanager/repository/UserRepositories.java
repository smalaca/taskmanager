package org.smalaca.taskmanager.repository;

public class UserRepositories {
    public static InMemoryUserRepository aInMemoryUserRepository() {
        return new InMemoryUserRepository();
    }
}
