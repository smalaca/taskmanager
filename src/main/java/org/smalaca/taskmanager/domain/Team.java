package org.smalaca.taskmanager.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<User> members = new ArrayList<>();

    public User getMemberByRole(TeamRole teamRole) {
        for (User user : members) {
            if (teamRole.equals(user.getTeamRole())){
                return user;
            }
        }

        return null;
    }

    public List<User> getMembers() {
        return members;
    }
}
