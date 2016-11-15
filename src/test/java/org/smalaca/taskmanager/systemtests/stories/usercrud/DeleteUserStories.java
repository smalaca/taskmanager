package org.smalaca.taskmanager.systemtests.stories.usercrud;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.runner.RunWith;
import org.smalaca.taskmanager.Application;
import org.smalaca.taskmanager.systemtests.JBehaveConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class DeleteUserStories extends JBehaveConfiguration {
    private String userId;

    @Given("a user id")
    public void givenUserData() {
        userId = "1";
    }

    @When("sends a request")
    public void whenDeleteUser() {
        getRestClient().deleteUser(userId);
    }

    @Then("user is removed")
    public void thenUserIsRemoved() {
        assertThat(getRestClient().getNonExistingUser(userId)).isEqualTo(NOT_FOUND);
    }
}
