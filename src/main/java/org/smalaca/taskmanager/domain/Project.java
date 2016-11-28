package org.smalaca.taskmanager.domain;

public class Project {
    private final ProductOwner productOwner;

    public Project(ProductOwner productOwner) {
        this.productOwner = productOwner;
    }

    public ProductOwner getProductOwner() {
        return productOwner;
    }
}
