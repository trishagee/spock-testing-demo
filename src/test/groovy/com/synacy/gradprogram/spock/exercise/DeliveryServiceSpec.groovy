package com.synacy.gradprogram.spock.exercise

import spock.lang.Specification

class DeliveryServiceSpec extends Specification {

    DeliveryService service
    DateUtils dateUtils = Mock(DateUtils)
    DeliveryRequestRepository deliveryRequestRepository = Mock(DeliveryRequestRepository)

    def setup() {
        service = new DeliveryService(dateUtils, deliveryRequestRepository)
    }

    def "createDelivery Should save the Order in delivery Request Repository with the correct details"() {
        given:
        UUID orderId = UUID.randomUUID()
        Order order = new Order(orderId, 10)
        Date deliveryDate = dateUtils.getCurrentDate()
        Courier courier = Courier.JRS
        when:
        DeliveryRequest deliveryRequest = service.createDelivery(order)
        then:
        1 * deliveryRequestRepository.saveDeliveryRequest(_) >> { DeliveryRequest savedDeliveryRequest ->
            assert orderId == saveDeliveryRequest.getOrderID()
            assert deliveryDate == savedDeliveryRequest.getDeliveryDate()
            assert courier.JRS == savedDeliveryRequest.getCourier()

        }
    }

    def "determineCourier should be able to determine which Courier shipment according the Total Cost"() {
        given:
        UUID = order.getId()
        Order order = new Order(orderId, orderTotalCost)


        when:
        Courier actualCourier = service.determineCourier(orderTotalCost)

        then:
        expectedCourier == actualCourier

        where:
        orderTotalCost | expectedCourier
        14             | Courier.JRS
        27             | Courier.GRAB
        63             | Courier.LBC

    }
}
