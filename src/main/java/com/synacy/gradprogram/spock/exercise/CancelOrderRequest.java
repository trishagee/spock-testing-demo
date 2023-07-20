package com.synacy.gradprogram.spock.exercise;

import java.util.Date;
import java.util.UUID;

public class CancelOrderRequest {
  private UUID orderId;
  private CancelReason reason;
  private Date dateCancelled;

  public UUID getOrderId() {
    return orderId;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
  }

  public CancelReason getReason() {
    return reason;
  }

  public void setReason(CancelReason reason) {
    this.reason = reason;
  }

  public Date getDateCancelled() {
    return dateCancelled;
  }

  public void setDateCancelled(Date dateCancelled) {
    this.dateCancelled = dateCancelled;
  }
}
