package com.synacy.gradprogram.spock.demo;

import java.util.List;

public class UserService {

  private final UserValidatorService userValidatorService;
  private final UserRepository userRepository;
  private final UserGroupService userGroupService;

  public UserService(UserValidatorService userValidatorService, UserRepository userRepository,
      UserGroupService userGroupService) {
    this.userValidatorService = userValidatorService;
    this.userRepository = userRepository;
    this.userGroupService = userGroupService;
  }

  public User createUser(Long id, String firstName, String lastName, String address, int age, CivilStatus civilStatus) {
    if (!userValidatorService.isUserAgeValid(age)) {
      throw new AgeIsBelowAgeOfConsentException("Age should be above 18");
    }

    User user = new User();
    user.setId(id);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setAddress(address);
    user.setAge(age);
    user.setCivilStatus(civilStatus);

    userRepository.saveUser(user);

    return user;
  }

  public void addUsersToGroup(List<Long> userIds, UserGroup userGroup) {
    for (Long userId : userIds) {
      User user = userRepository.fetchUserById(userId);
      user.setUserGroup(userGroup);

      userGroupService.userAddedToGroup(userGroup);

      userRepository.saveUser(user);
    }
  }

}
