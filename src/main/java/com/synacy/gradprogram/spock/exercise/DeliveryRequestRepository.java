package com.synacy.gradprogram.spock.exercise;

import java.util.UUID;

public class DeliveryRequestRepository {

  public void saveDeliveryRequest(DeliveryRequest deliveryRequest) {
    // This method saves the given DeliveryRequest to the database
  }

  public DeliveryRequest fetchDeliveryRequestByOrderId(UUID orderId) {
    // This method fetches and responds with the DeliveryRequest using the given orderId from the database
    return new DeliveryRequest();
  }
}
