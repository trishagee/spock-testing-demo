package com.synacy.gradprogram.spock.demo;

import java.util.Date;

public class UserGroupService {

  private final UserGroupRepository userGroupRepository;
  private final DateService dateService;

  public UserGroupService(UserGroupRepository userGroupRepository, DateService dateService) {
    this.userGroupRepository = userGroupRepository;
    this.dateService = dateService;
  }

  public UserGroup createUserGroup(Long id,String userGroupName,int totalUserInGroup,Date date) {
    UserGroup userGroup = new UserGroup();
    userGroup.setId(id);
    userGroup.setUserGroupName(userGroupName);
    userGroup.setTotalUsersInGroup(totalUserInGroup);
    userGroup.setLastUserAddedDate(date);

    userGroupRepository.saveUserGroup(userGroup);

    return userGroup;
  }

  public void userAddedToGroup(UserGroup userGroup) {
    int newUserTotal = userGroup.getTotalUsersInGroup() + 1;
    userGroup.setTotalUsersInGroup(newUserTotal);
    userGroup.setLastUserAddedDate(dateService.getDateNow());

    userGroupRepository.updateUserGroup(userGroup);
  }

  public void userRemovedToGroup(UserGroup userGroup) {
    if(userGroup.getTotalUsersInGroup()==0){
      throw new EmptyUserGroupException();
    }

    int newUserTotal = userGroup.getTotalUsersInGroup() - 1;
    userGroup.setTotalUsersInGroup(newUserTotal);

    userGroupRepository.updateUserGroup(userGroup);
  }
}
