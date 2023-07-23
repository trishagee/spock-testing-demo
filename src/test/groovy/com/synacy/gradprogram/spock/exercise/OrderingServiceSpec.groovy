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
        CancelOrderRequest request = new CancelOrderRequest()
        Order order = new Order()
        Order orderId = UUID.randomUUID()
        String recipient = "Clark"
        String address = "Cebu"
        CancelReason reason = CancelReason.DAMAGED
        Order orderStatus = OrderStatus.PENDING
        CancelOrderRequest dateCancelled = request.setDateCancelled(new Date())



        when:
        RefundRequest refundRequest = service.cancelOrder(request, order)

        then:
        1 * refundRepository.saveRefundRequest(refundRequest)


    }
}
