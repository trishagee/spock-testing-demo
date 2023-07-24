package com.synacy.gradprogram.spock.exercise

import com.synacy.gradprogram.spock.demo.User
import spock.lang.Specification

class ShoppingServiceSpec extends Specification {

    ShoppingService service

    OrderingService orderingService = Mock(OrderingService)
    DeliveryService deliveryService = Mock(DeliveryService)
    OrderRepository orderRepository = Mock(OrderRepository)
    DeliveryRequestRepository deliveryRequestRepository = Mock(DeliveryRequestRepository)

    def setup() {
        service = new ShoppingService()
    }

    def "buyNonSpoilingItemsInCart Should be able to create delivery order"() {
        given:
        def items = [
                new Item("Chicken Cordon", 40, ItemType.FOOD),
                new Item("Trouser", 100, ItemType.CLOTHING),
                new Item("TV", 100, ItemType.APPLIANCE),
                new Item("Charger", 300, ItemType.GADGET),
                new Item("Pancit Malabon", 100, ItemType.FOOD),
                new Item("Headset", 500, ItemType.GADGET)
        ]
        Cart itemsInTheCart = new Cart(UUID.randomUUID(), items)

        User user = new User()
        service = new ShoppingService()(orderingService, deliveryService)

        Order order = new Order()
        order.setRecipientName(user.getFirstName() + " " + user.getLastName())
        orderingService.createAnOrder() >> order
        when:
        service.buyNonSpoilingItemsInCart(itemsInTheCart, user)

        then:
        1 * deliveryService.createDelivery(_) { DeliveryRequestRepository deliveryRequestRepository ->
            assert createOrder == orderingService.createAnOrder()
            assert recipient == createOrder.getRecipientName()

        }
    }

    def "getOrderSummary Should get the Order summary based on Order ID"() {
        given:
        UUID orderId = UUID.randomUUID()
        Order order = new Order(orderId 10, OrderStatus.PENDING)
        DeliveryRequest deliveryRequest = new DeliveryRequest(orderId, new Date(), Courier.JRS)

        orderRepository.fetchOrderById(orderId) >> order
        deliveryRequestRepository.fetchDeliveryRequestByOrderId(orderId) >> deliveryRequest

        when:
        OrderSummary orderSummary = shoppingService.getOrderSummary(orderId)

        then:
        order.getTotalCost() == orderSummary.getTotalCost()
        order.getStatus() == orderSummary.getStatus()
        deliveryRequest.getDeliveryDate() == orderSummary.getDeliveryDate()
        deliveryRequest.getCourier() == orderSummary.getCourier()
    }
}

