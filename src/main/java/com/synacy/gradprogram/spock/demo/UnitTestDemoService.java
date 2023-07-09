package com.synacy.gradprogram.spock.demo;

import java.util.List;

public class UnitTestDemoService {

  public int sum(int num1, int num2) {
    return num1 + num2;
  }

  public double calculateAverage(List<Integer> numbers) {
    int total = 0;
    for (int number : numbers) {
      total += number;
    }

    return (double) total /numbers.size();
  }

  public User createUser(String firstName, String lastName, String address, int age, CivilStatus civilStatus) {
    if (age < 18) {
      throw new AgeIsBelowAgeOfConsentException("Age should be above 18");
    }

    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setAddress(address);
    user.setAge(age);
    user.setCivilStatus(civilStatus);

    return user;
  }
}
