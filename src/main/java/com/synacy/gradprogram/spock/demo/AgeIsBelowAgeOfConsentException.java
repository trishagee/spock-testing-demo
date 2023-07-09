package com.synacy.gradprogram.spock.demo;

public class AgeIsBelowAgeOfConsentException extends RuntimeException {

  public AgeIsBelowAgeOfConsentException(String errorMessage) {
    super(errorMessage);
  }
}
