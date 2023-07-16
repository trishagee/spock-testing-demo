package com.synacy.gradprogram.spock.demo;

import java.util.Date;

public class UserGroup {

  private Long id;
  private String userGroupName;
  private int totalUsersInGroup;
  private Date lastUserAddedDate;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserGroupName() {
    return userGroupName;
  }

  public void setUserGroupName(String userGroupName) {
    this.userGroupName = userGroupName;
  }

  public int getTotalUsersInGroup() {
    return totalUsersInGroup;
  }

  public void setTotalUsersInGroup(int totalUsersInGroup) {
    this.totalUsersInGroup = totalUsersInGroup;
  }

  public Date getLastUserAddedDate() {
    return lastUserAddedDate;
  }

  public void setLastUserAddedDate(Date lastUserAddedDate) {
    this.lastUserAddedDate = lastUserAddedDate;
  }
}
