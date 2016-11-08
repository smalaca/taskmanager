package org.smalaca.taskmanager.systemtests.stories.usercrud;

import org.jbehave.core.annotations.Given;
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

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.smalaca.taskmanager.systemtests.rest.RestClient.hostName;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class AddUserStories extends JBehaveConfiguration {
    private UserDto user;
    private URI userUri;

    @Given("a new user data")
    public void givenANewUser() {
        user = new UserDto("Sebastian");
    }

    @When("sends a request")
    public void whenCreatesNewUser() {
        userUri = RestClient.createAUser(user);
    }

    @Then("user is created")
    public void thenUserIsCreated() {
        assertThat(userUri.toASCIIString()).isEqualTo(hostName() + "/user/13");
    }
}
