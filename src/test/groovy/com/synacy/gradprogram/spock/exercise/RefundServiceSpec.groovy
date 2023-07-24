package com.synacy.gradprogram.spock.exercise

import spock.lang.Specification

class RefundServiceSpec extends Specification {

    RefundService service

    CancelOrderRequest cancelOrderRequest = Mock(CancelOrderRequest)
    RefundRepository refundRepository = Mock(RefundRepository)
    OrderRepository orderRepository = Mock(OrderRepository)

    def setup(){
        service = new RefundService()
    }

    def "calculateRefund should calculate the refund if CancelReason is CancelReason.DAMAGED."(){
        given:
        cancelOrderRequest.getOrderId() >> UUID.randomUUID()
        cancelOrderRequest.getDateCancelled() >> new Date()
        cancelOrderRequest.getReason() >> CancelReason.DAMAGED



        when:
        RefundRequest refund = service.calculateRefund(cancelOrderRequest)

        then:
        refundRepository.saveRefundRequest(refund)
    }
}
