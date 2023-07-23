package com.synacy.gradprogram.spock.exercise

import spock.lang.Specification

class OrderingServiceSpec extends Specification {

    OrderingService service

    OrderRepository orderRepository = Mock(OrderRepository)
    RefundRepository refundRepository = Mock(RefundRepository)


    def setup(){
        service = new OrderingService()
    }

    def "cancelOrder should cancel orders for PENDING and FOR_DELIVERY orders."(){
        given:
        UUID id = UUID.randomUUID()
        orderRepository.fetchOrderById(id)

        Order order = new Order(id:id, totalCost: 500, recipientName: "Clark", recipientAddress: "Cebu", status: OrderStatus.FOR_DELIVERY)
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest(reason: CancelReason.DAMAGED, dateCancelled: new Date())

        when:
        RefundRequest refundRequest = service.cancelOrder(cancelOrderRequest, order)

        then:
        1 * refundRepository.saveRefundRequest(refundRequest)

    }

    def "cancelOrder should throw UnableToCancelException if order status is not OrderStatus.PENDING and OrderStatus.FOR_DELIVERY."(){
        given:
        CancelOrderRequest cancelOrderRequest = Mock(CancelOrderRequest)
        Order order = Mock(Order)
        order.getStatus() >> OrderStatus.DELIVERED

        when:
        service.cancelOrder(cancelOrderRequest, order)

        then:
        thrown(UnableToCancelException)

    }

}
