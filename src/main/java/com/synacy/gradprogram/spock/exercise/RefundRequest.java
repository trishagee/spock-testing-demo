package com.synacy.gradprogram.spock.exercise;

import java.math.BigDecimal;
import java.util.UUID;

public class RefundRequest {
  private String recipientName;
  private UUID orderId;
  private double refundAmount;
  private RefundRequestStatus status;

  public String getRecipientName() {
    return recipientName;
  }

  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }

  public UUID getOrderId() {
    return orderId;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
  }

  public double getRefundAmount() {
    return refundAmount;
  }

  public void setRefundAmount(double refundAmount) {
    this.refundAmount = refundAmount;
  }

  public RefundRequestStatus getStatus() {
    return status;
  }

  public void setStatus(RefundRequestStatus status) {
    this.status = status;
  }
}
