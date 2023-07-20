package com.synacy.gradprogram.spock.demo;

import java.util.Date;

public class UserGroupService {

  private final UserGroupRepository userGroupRepository;

  public UserGroupService(UserGroupRepository userGroupRepository) {
    this.userGroupRepository = userGroupRepository;
  }

  public UserGroup createUserGroup() {
    // TODO: Implement me
    return null;
  }

  public void userAddedToGroup(UserGroup userGroup) {
    int newUserTotal = userGroup.getTotalUsersInGroup() + 1;
    userGroup.setTotalUsersInGroup(newUserTotal);
    userGroup.setLastUserAddedDate(new Date());

    userGroupRepository.updateUserGroup(userGroup);
  }

  public void userRemovedToGroup(UserGroup userGroup) {
    // TODO: Implement me
  }
}
