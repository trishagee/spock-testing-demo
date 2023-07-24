package com.synacy.gradprogram.spock.exercise;

import java.math.BigDecimal;


public class RefundService {

  public double calculateRefund(CancelOrderRequest cancelOrderRequest, Order order) {
    // TODO: Implement me. Full refund if cancel reason is due to damaged item.
    //  Also full refund if the order was cancelled within 3 days of order date, else refund half of the total cost.
    OrderRepository orderRepository = new OrderRepository();

    orderRepository.fetchOrderById(cancelOrderRequest.getOrderId());

    RefundRequest refund = new RefundRequest();
    RefundRepository refundRepository = new RefundRepository();

    if(cancelOrderRequest.getReason() == CancelReason.DAMAGED){
      refund.setRefundAmount(order.getTotalCost());

      refundRepository.saveRefundRequest(refund);

    }

    return refund.getRefundAmount();
  }

  private void createAndSaveRefundRequest(CancelOrderRequest cancelOrderRequest) {
    // TODO: Implement me. Creates a TO_PROCESS refund request and saves it to the database




  }

}
