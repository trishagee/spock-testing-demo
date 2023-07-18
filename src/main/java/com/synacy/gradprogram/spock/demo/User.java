package com.synacy.gradprogram.spock.demo;

public class User {

  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  private int age;
  private CivilStatus civilStatus;
  private UserGroup userGroup;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public CivilStatus getCivilStatus() {
    return civilStatus;
  }

  public void setCivilStatus(CivilStatus civilStatus) {
    this.civilStatus = civilStatus;
  }

  public UserGroup getUserGroup() {
    return userGroup;
  }

  public void setUserGroup(UserGroup userGroup) {
    this.userGroup = userGroup;
  }
}
