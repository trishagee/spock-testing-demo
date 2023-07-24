package com.synacy.gradprogram.spock.exercise

import spock.lang.Specification

class RefundServiceSpec extends Specification {

    RefundService service

    CancelOrderRequest cancelOrderRequest = Mock(CancelOrderRequest)
    RefundRepository refundRepository = Mock(RefundRepository)
    OrderRepository orderRepository = Mock(OrderRepository)
    Order order = Mock(Order)

    def setup(){
        service = new RefundService()
    }

    def "calculateRefund should calculate the refund if CancelReason is CancelReason.DAMAGED."(){
        given:
        cancelOrderRequest.getOrderId() >> UUID.randomUUID()
        cancelOrderRequest.getDateCancelled() >> new Date()
        cancelOrderRequest.getReason() >> CancelReason.DAMAGED
        order.getTotalCost() >> 500.0


        RefundRequest refund = Mock(RefundRequest)

        when:
        service.calculateRefund(cancelOrderRequest, order)

        then:
        refundRepository.saveRefundRequest(refund)
    }

    def "calculateRefund should full refund if the order was cancelled within 3 days of order date"(){
        cancelOrderRequest.getOrderId() >> orderRepository.fetchOrderById(UUID.randomUUID())
        cancelOrderRequest.getDateCancelled() >> new Date()
        cancelOrderRequest.getReason() >> CancelReason.DAMAGED
        order.getTotalCost() >> 500.0
        order.getDateOrdered() >> 2023-07-16
        cancelOrderRequest.dateCancelled >> 2023-07-17

        RefundRequest refund = Mock(RefundRequest)

        when:
        service.calculateRefund(cancelOrderRequest, order)

        then:
        refundRepository.saveRefundRequest(refund)
    }
}
