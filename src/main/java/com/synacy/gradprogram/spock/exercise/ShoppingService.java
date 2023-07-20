package com.synacy.gradprogram.spock.exercise;

import com.synacy.gradprogram.spock.demo.User;
import java.util.UUID;

public class ShoppingService {

  private final OrderingService orderingService;
  private final DeliveryService deliveryService;
  private final OrderRepository orderRepository;
  private final DeliveryRequestRepository deliveryRequestRepository;

  public ShoppingService(OrderingService orderingService, DeliveryService deliveryService,
      OrderRepository orderRepository, DeliveryRequestRepository deliveryRequestRepository) {
    this.orderingService = orderingService;
    this.deliveryService = deliveryService;
    this.orderRepository = orderRepository;
    this.deliveryRequestRepository = deliveryRequestRepository;
  }

  public void buyNonSpoilingItemsInCart(Cart cart, User user) {
    orderingService.applyDiscountToCartItems(cart);

    String recipientName = user.getFirstName().concat(" ").concat(user.getLastName());
    Order order = orderingService.createAnOrder(cart, recipientName, user.getAddress(), false);

    deliveryService.createDelivery(order);
  }

  public OrderSummary getOrderSummary(UUID orderId) {
    Order order = orderRepository.fetchOrderById(orderId);
    DeliveryRequest deliveryRequest = deliveryRequestRepository.fetchDeliveryRequestByOrderId(orderId);

    return new OrderSummary(order.getTotalCost(), order.getStatus(),
        deliveryRequest.getDeliveryDate(), deliveryRequest.getCourier());
  }
}
