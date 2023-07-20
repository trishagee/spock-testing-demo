package com.synacy.gradprogram.spock.exercise;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class OrderRepository {

  public void saveOrder(Order order) {
    // This method saves the given Order to the database
    System.out.println("Saving RefundRequest to DB with details: "
        + order.getId() + ", "
        + order.getStatus()
    );
  }

  public Optional<Order> fetchOrderById(UUID id) {
    // This method fetches and responds with the Order using the given id from the database
    return Optional.empty();
  }
}
