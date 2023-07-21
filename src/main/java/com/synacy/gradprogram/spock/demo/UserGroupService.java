package com.synacy.gradprogram.spock.demo;

import java.util.Date;

public class UserGroupService {

  private final UserGroupRepository userGroupRepository;
  private final DateService dateService;

  public UserGroupService(UserGroupRepository userGroupRepository, DateService dateService) {
    this.userGroupRepository = userGroupRepository;
    this.dateService = dateService;
  }

  public void userAddedToGroup(UserGroup userGroup) {
    int newUserTotal = userGroup.getTotalUsersInGroup() + 1;
    userGroup.setTotalUsersInGroup(newUserTotal);
    userGroup.setLastUserAddedDate(dateService.getDateNow());

    userGroupRepository.updateUserGroup(userGroup);
  }
}
