package org.smalaca.taskmanager.systemtests.stories.usercrud;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.runner.RunWith;
import org.smalaca.taskmanager.Application;
import org.smalaca.taskmanager.systemtests.JBehaveConfiguration;
import org.smalaca.taskmanager.systemtests.rest.RestClient;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class GetAllUsersStories extends JBehaveConfiguration {
    private ResponseEntity<List> allUsersResponse;

    @When("retrieves all users")
    public void retrieveAllUsers() {
        allUsersResponse = RestClient.getAllUsers();
    }

    @Then("NO_CONTENT http status returns")
    public void thenNoContentWasReturn() {
        assertThat(allUsersResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
