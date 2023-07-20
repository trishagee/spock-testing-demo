package com.synacy.gradprogram.spock.exercise;

import java.util.UUID;

public class Order {

  private final UUID id;
  private double totalCost;
  private String recipientName;
  private String recipientAddress;
  private OrderStatus status;

  public Order() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public String getRecipientName() {
    return recipientName;
  }

  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }

  public String getRecipientAddress() {
    return recipientAddress;
  }

  public void setRecipientAddress(String recipientAddress) {
    this.recipientAddress = recipientAddress;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }
}
