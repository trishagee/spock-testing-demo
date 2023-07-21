package com.synacy.gradprogram.spock.exercise;

import java.util.List;
import java.util.UUID;

public class Cart {


  private final UUID id;

  private List<Item> items;

  private int totalItems;

  public Cart(UUID id, List<Item> items) {
    this.id = id;
    this.items = items;
    this.totalItems = items.size();
  }

  public UUID getId() {
    return id;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public void addItem(Item item) {
    this.items.add(item);
  }

  public void removeItem(Item item) {
    this.items.remove(item);
  }

  public int getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(int totalItems) {
    this.totalItems = totalItems;
  }
}
