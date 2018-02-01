package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private ProductOwner productOwner;
    private List<Team> teams = new ArrayList<>();

    public Project(ProductOwner productOwner, String name) {
        this.productOwner = productOwner;
        this.name = name;
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
}
