package org.smalaca.taskmanager.systemtests.stories;

import org.assertj.core.api.StrictAssertions;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.runner.RunWith;
import org.smalaca.taskmanager.Application;
import org.smalaca.taskmanager.systemtests.JBehaveConfiguration;
import org.smalaca.taskmanager.systemtests.dto.UserDto;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class UserCrudStories extends JBehaveConfiguration {
    private List<UserDto> allUsers;
    private String userId;
    private UserDto newUser;
    private UserDto user;
    private UserDto editedUser;

    @When("retrieves all users")
    public void whenRetrievesAllUser() {
        allUsers = getRestClient().getAllUsers();
    }

    @Then("the list of the users contains ${usersAmount} user")
    public void thenTheListOfUserContains(int usersAmount) {
        assertThat(allUsers.size()).isEqualTo(usersAmount);
    }

    @Given("a new user data")
    public void givenNewUserData() {
        newUser = new UserDto();
        newUser.setFirstName("Felicia");
        newUser.setLastName("Hardy");
        newUser.setLogin("blackcat");
        newUser.setPassword("don't like spider man no more");
    }

    @When("adds a new user")
    public void whenAddsNewUser() {
        URI newlyCreatedUserUri = getRestClient().createUser(newUser);
        userId = newlyCreatedUserUri.getPath().replace("/user/", "");
    }

    @When("retrieves information about the user")
    public void whenRetrievesInformationAboutUser() {
        user = getRestClient().getUser(userId);
    }

    @Then("the newly created user exists")
    public void thenNewlyCreatedUserExists() {
        assertThat(user).isEqualToIgnoringGivenFields(newUser, "id");
    }

    @Given("a new data for newly created user")
    public void givenNewDataForNewlyCreatedUser() {
        editedUser = new UserDto();
        editedUser.setPassword("or maybe I do");
    }

    @When("updates a user")
    public void whenUpdatesUser() {
        getRestClient().updateUser(userId, editedUser);
    }

    @Then("user's data are changed")
    public void thenUserDataAreChanged() {
        assertThat(user).isEqualToIgnoringGivenFields(newUser, "id", "password");
        assertThat(user).isEqualToComparingOnlyGivenFields(editedUser, "password");
    }

    @When("removes a user")
    public void whenRemovesUser() {
        getRestClient().deleteUser(userId);
    }

    @Then("the removed user is not found")
    public void thenRemovedUserIsNotFound() {
        StrictAssertions.assertThat(getRestClient().getNonExistingUser(userId)).isEqualTo(NOT_FOUND);
    }
}
