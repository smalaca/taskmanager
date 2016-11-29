package org.smalaca.taskmanager.domain;

public class Sprint {
    private final Project project;
    private final String name;

    public Sprint(Project project, String name) {
        this.project = project;
        this.name = name;
    }

    public boolean differentThan(Sprint sprint) {
        return project.sameAs(project) && name.equals(sprint.name);
    }
}
