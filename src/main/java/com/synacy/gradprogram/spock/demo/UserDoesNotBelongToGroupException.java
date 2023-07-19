package com.synacy.gradprogram.spock.demo;

public class UserDoesNotBelongToGroupException extends RuntimeException {

  public UserDoesNotBelongToGroupException(String errorMessage) {
    super(errorMessage);
  }
}
