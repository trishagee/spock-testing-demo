package com.synacy.gradprogram.spock.exercise

import spock.lang.Specification

class OrderingServiceSpec extends Specification {

    OrderingService service

    OrderRepository orderRepository = Mock(OrderRepository)


    def setup(){
        service = new OrderingService()
    }

    def "cancelOrder should cancel orders for PENDING and FOR_DELIVERY orders."(){
        given:
        UUID orderId = UUID.randomUUID()
        String recipient = "Clark"
        String address = "Cebu"order
        CancelReason reason = CancelReason.DAMAGED
        Order orderStatus = OrderStatus.PENDING


        CancelOrderRequest request = orderRepository.fetchDeliveryRequestByOrderId(orderId)

        when:
        service.cancelOrder(request)

        then:
        1 * OrderRepository.saveOrder(_){ Order order ->
            assert orderId == order.getId()
            assert reason == request.getReason()

        }

    }
}
