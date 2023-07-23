package com.synacy.gradprogram.spock.exercise;

import java.util.Date;

public class OrderingService {

  private final OrderRepository orderRepository;

  private  final RefundRepository refundRepository;

  private final DeliveryRequestRepository deliveryRequestRepository;

  public OrderingService(OrderRepository orderRepository, RefundRepository refundRepository, DeliveryRequestRepository deliveryRequestRepository) {
    this.orderRepository = orderRepository;
    this.refundRepository = refundRepository;
    this.deliveryRequestRepository = deliveryRequestRepository;
  }

  public boolean cartContainsFoodItem(Cart cart) {
    for (Item item : cart.getItems()) {
      if (item.getType() == ItemType.FOOD) {
        return true;
      }
    }

    return false;
  }

  public double calculateTotalCostOfCart(Cart cart) {
    double totalPrice = 0.0;

    for (Item item : cart.getItems()) {
      totalPrice += item.getCost();
    }

    return totalPrice;
  }

  public boolean isCartEligibleForDiscount(Cart cart) {
    double totalPrice = calculateTotalCostOfCart(cart);
    double discountTotalAmountThreshold = 50.0;
    int itemCountDiscountThreshold = 5;

    return totalPrice > discountTotalAmountThreshold
        && cart.getItems().size() > itemCountDiscountThreshold;
  }

  public void applyDiscountToCartItems(Cart cart) {
    double discountRate = 0.10;
    if (isCartEligibleForDiscount(cart)) {
      for (Item item : cart.getItems()) {
        double discountedCost = item.getCost() * discountRate;
        item.setCost(discountedCost);
      }
    }
  }

  public Order createAnOrder(Cart cart, String recipientName, String recipientAddress, boolean canContainFood) {
    if (!canContainFood && cartContainsFoodItem(cart)) {
      throw new UnableToCreateOrderException("Cart should not contain FOOD items");
    }

    Order order = new Order();
    order.setTotalCost(calculateTotalCostOfCart(cart));
    order.setRecipientName(recipientName);
    order.setRecipientAddress(recipientAddress);
    order.setStatus(OrderStatus.PENDING);
    order.setDateOrdered(new Date());

    orderRepository.saveOrder(order);

    return order;
  }

  public void cancelOrder(CancelOrderRequest cancelOrderRequest, Order order) {
    // TODO: Implement me. Cancels PENDING and FOR_DELIVERY orders and create a refund request saving it to the database.
    //  Else throws an UnableToCancelException

    RefundRequest refundRequest = new RefundRequest();
    orderRepository.fetchOrderById(order.getId());

    if (order.getStatus() == OrderStatus.PENDING ||
            order.getStatus() == OrderStatus.FOR_DELIVERY) {


      order.setStatus(OrderStatus.CANCELLED);
      cancelOrderRequest.setDateCancelled(new Date());
      cancelOrderRequest.setReason(cancelOrderRequest.getReason());

      refundRequest.setOrderId(cancelOrderRequest.getOrderId());
      refundRequest.setRefundAmount(order.getTotalCost());
      refundRequest.setStatus(RefundRequestStatus.TO_PROCESS);

    }

    orderRepository.saveOrder(order);
    refundRepository.saveRefundRequest(refundRequest);
  }
}
