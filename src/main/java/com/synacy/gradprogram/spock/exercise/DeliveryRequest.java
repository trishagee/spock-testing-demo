package com.synacy.gradprogram.spock.exercise;

import java.util.Date;
import java.util.UUID;

public class DeliveryRequest {

  private final UUID id;
  private UUID orderId;
  private Date deliveryDate;
  private Courier courier;

  public DeliveryRequest() {
    id = UUID.randomUUID();
  }

  public UUID getOrderId() {
    return orderId;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public Courier getCourier() {
    return courier;
  }

  public void setCourier(Courier courier) {
    this.courier = courier;
  }
}
