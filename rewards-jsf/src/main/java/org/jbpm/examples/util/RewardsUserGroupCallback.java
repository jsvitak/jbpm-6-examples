package org.jbpm.examples.util;

import org.kie.api.task.UserGroupCallback;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class RewardsUserGroupCallback implements UserGroupCallback {

    public boolean existsUser(String userId) {
        return userId.equals("john") || userId.equals("mary") || userId.equals("Administrator");
    }

    public boolean existsGroup(String groupId) {
        return groupId.equals("PM") || groupId.equals("HR");
    }

    public List<String> getGroupsForUser(String userId,
                                         List<String> groupIds, List<String> allExistingGroupIds) {
        List<String> groups = new ArrayList<String>();
        if (userId.equals("john"))
            groups.add("PM");
        else if (userId.equals("mary"))
            groups.add("HR");
        return groups;
    }
}
