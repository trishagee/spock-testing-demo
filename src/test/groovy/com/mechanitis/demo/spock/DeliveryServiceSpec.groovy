package com.mechanitis.demo.spock

import com.synacy.gradprogram.spock.exercise.*
import spock.lang.Specification

class DeliveryServiceSpec extends Specification {
    DateUtils dateUtils
    Order order
    DeliveryRequestRepository deliveryRequestRepository
    DeliveryService deliveryService

    void setup() {
        dateUtils = Mock(DateUtils)
        order = Mock(Order)
        deliveryRequestRepository = Mock(DeliveryRequestRepository)
        deliveryService = new DeliveryService(dateUtils, deliveryRequestRepository)
    }

    def "This is for Creating Order"() {
        given:
        def expectedDeliveryDate = new Date()
        dateUtils.getCurrentDate() >> expectedDeliveryDate

        UUID nameId = UUID.randomUUID()
        order.getId() >> nameId

        order.getTotalCost() >> totalCost

        when:
        deliveryService.createDelivery(order)

        then:

        1 * deliveryRequestRepository.saveDeliveryRequest(_) >> { DeliveryRequest deliveryRequest ->
            assert expectedDeliveryDate == deliveryRequest.getDeliveryDate()
            assert nameId == deliveryRequest.getOrderId()
            assert courierName = deliveryRequest.getCourier()
        }

        where:
        totalCost | courierName
        120 Courier.GRAB
        180 Courier.JRS
        150 Courier.LBC

    }

    def "Create delivery service will show delivery request with exact details"() {
        given:
        def expectedDeliveryDate = new Date()
        dateUtils.getCurrentDate() >> expectedDeliveryDate

        UUID nameId = UUID.randomUUID()
        order.getId() >> nameId

        order.getTotalCost() >> totalCost

        when:
        DeliveryRequest deliveryRequest = deliveryService.createDelivery(order)

        then:
        expectedDeliveryDate == deliveryRequest.getDeliveryDate()
        nameId == deliveryRequest.getOrderId()
        courierName = deliveryRequest.getCourier()

        where:
        totalCost | courierName
        120 Courier.GRAB
        180 Courier.JRS
        150 Courier.LBC

    }
}
