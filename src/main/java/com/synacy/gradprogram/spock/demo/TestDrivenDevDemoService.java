package com.synacy.gradprogram.spock.demo;

public class TestDrivenDevDemoService {

  public Double sum(double num1, double num2) {
    return num1 + num2;
  }

  public Double divide(double dividend, double divisor) {
    if (divisor == 0) {
      throw new InfinityOperationException("Exception message");
    }

    return dividend/divisor;
  }

}
