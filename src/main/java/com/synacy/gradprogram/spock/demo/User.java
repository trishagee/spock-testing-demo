package com.synacy.gradprogram.spock.demo;

public class User {

  private String firstName;
  private String lastName;
  private String address;
  private int age;
  private CivilStatus civilStatus;

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
}
