package com.synacy.gradprogram.spock.exercise;

import java.util.UUID;

public class Item {

  private final UUID id;
  private String name;
  private double cost;
  private ItemType type;

  public Item(String name, double cost, ItemType type) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.cost = cost;
    this.type = type;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }
}
