package com.synacy.gradprogram.spock.exercise;

import java.math.BigDecimal;
import java.util.UUID;

public class RefundRequest {
  private String recipientName;
  private UUID orderId;
  private BigDecimal refundAmount;
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

  public BigDecimal getRefundAmount() {
    return refundAmount;
  }

  public void setRefundAmount(BigDecimal refundAmount) {
    this.refundAmount = refundAmount;
  }

  public RefundRequestStatus getStatus() {
    return status;
  }

  public void setStatus(RefundRequestStatus status) {
    this.status = status;
  }
}
