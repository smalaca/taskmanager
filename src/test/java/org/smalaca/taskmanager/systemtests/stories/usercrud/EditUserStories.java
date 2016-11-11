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

import static org.assertj.core.api.StrictAssertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class EditUserStories extends JBehaveConfiguration {
    private static final String USER_ID = "1";
    private UserDto user;

    @Given("a user data")
    public void givenUserData() {
        user = new UserDto();
        user.setName("Sebastian");
    }

    @When("sends a request")
    public void whenUpdatesUser() {
        RestClient.updateUser(USER_ID, user);
    }

    @Then("user is updated")
    public void thenUserIsUpdated() {
        assertThat(RestClient.getUser(USER_ID).getId()).isEqualTo(USER_ID);
    }
}
