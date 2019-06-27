package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

import static org.smalaca.taskmanager.domain.ProjectStatus.DEFINED;

public class Project {
    private ProjectStatus projectStatus = DEFINED;
    private String id;
    private String name;
    private ProductOwner productOwner;
    private List<Team> teams = new ArrayList<>();

    public Project(ProductOwner productOwner, String name) {
        this.productOwner = productOwner;
        this.name = name;
    }

    public void change(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public ProductOwner getProductOwner() {
        return productOwner;
    }

    public boolean sameAs(Project project) {
        return name.equals(project.name);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
