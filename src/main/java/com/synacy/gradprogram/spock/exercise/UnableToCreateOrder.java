package com.synacy.gradprogram.spock.exercise;

public class UnableToCreateOrder extends RuntimeException {

  public UnableToCreateOrder(String errorMessage) {
    super(errorMessage);
  }
}
