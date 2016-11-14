package org.smalaca.taskmanager.systemtests.stories.usercrud;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.runner.RunWith;
import org.smalaca.taskmanager.Application;
import org.smalaca.taskmanager.systemtests.JBehaveConfiguration;
import org.smalaca.taskmanager.systemtests.dto.UserDto;
import org.smalaca.taskmanager.systemtests.rest.RestClient;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class GetAllUsersStories extends JBehaveConfiguration {
    private List<UserDto> allUsersResponse;

    @When("retrieves all users")
    public void retrieveAllUsers() {
        allUsersResponse = RestClient.getAllUsers();
    }

    @Then("returns all stored users")
    public void thenReturnAllUsers() {
        assertThat(allUsersResponse.size()).isEqualTo(5);
    }
}
