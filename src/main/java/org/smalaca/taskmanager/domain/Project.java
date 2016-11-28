package org.smalaca.taskmanager.domain;

public class Project {
    private String name;
    private ProductOwner productOwner;

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
}
