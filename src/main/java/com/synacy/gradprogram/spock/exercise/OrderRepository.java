package com.synacy.gradprogram.spock.exercise;

import java.util.UUID;

public class OrderRepository {

  public void saveOrder(Order order) {
    // This method saves the given Order to the database
  }

  public Order fetchOrderById(UUID id) {
    // This method fetches and responds with the Order using the given id from the database
    return new Order();
  }
}
