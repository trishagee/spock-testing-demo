package com.synacy.gradprogram.spock.exercise;

public class UnableToCreateOrderException extends RuntimeException {

  public UnableToCreateOrderException(String errorMessage) {
    super(errorMessage);
  }
}
