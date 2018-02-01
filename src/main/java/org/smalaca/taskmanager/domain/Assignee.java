package org.smalaca.taskmanager.domain;

public class Assignee {
    private Team team;

    public Team getTeam() {
        return team;
    }

    public static Assignee from(Owner owner) {
        return null;
    }

    public static Assignee from(ProductOwner productOwner) {
        return null;
    }
}
