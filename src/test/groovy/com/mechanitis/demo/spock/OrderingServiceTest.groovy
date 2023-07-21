package com.mechanitis.demo.spock

import com.synacy.gradprogram.spock.exercise.*
import spock.lang.Specification

class OrderingServiceTest extends Specification {

    OrderingService service
    Cart cart

    def items = [
            new Item("Chicken Cordon", 25, ItemType.FOOD),
            new Item("Trouser", 1500, ItemType.CLOTHING),
            new Item("Smart-TV", 3500, ItemType.APPLIANCE),
            new Item("IPhone14Pro", 62000, ItemType.GADGET),
            new Item("Pancit Malabon", 110, ItemType.FOOD)
    ]

    def setup() {
        service = new OrderingService()
    }

    def "Validate the Items if there have food Order on the Cart"() {
        when:
        boolean validateCartItem = service.cartContainsFoodItem(cart = new Cart(UUID.randomUUID(), items))

        then:
        validateCartItem
    }

    def "Verify the total amount of cost items in the cart"() {
        when:
        double totalAmountPrice = service.calculateTotalCostOfCart(cart = new Cart(UUID.randomUUID(), items))
        then:
        totalAmountPrice
    }

    def "Check if the cart items eligible for discount"() {
        given:
        cart = new Cart(UUID.randomUUID(), items)

        when:
        boolean eligibleDiscountItem = service.isCartEligibleForDiscount(cart)

        then:
        eligibleDiscountItem
    }

    def "Check item if eligible to apply the discount"() {
        given:
        cart = new Cart(UUID.randomUUID(), items)
        List totalDiscount = cart.getItems().collect() { it.getCost() }

        when:
        service.applyDiscountToCartItems(cart)
        List itemDiscounted = cart.getItems().collect() { it.getCost() }

        then:
        itemDiscounted.size() == totalDiscount.size()

    }

    def "Created an Order with the Food type  and correct details "() {
        given:
        cart = new Cart(UUID.randomUUID(), items)

        String recipientName = "Precious"
        String recipientAddress = "Lapu-lapu City"
        boolean canContainFood = true

        when:
        Order order = service.createAnOrder(cart, recipientName, recipientAddress, canContainFood)

        then:
        order.totalCost == service.calculateTotalCostOfCart(cart)
        order.recipientName == recipientName
        order.recipientAddress == recipientAddress
        order.status == OrderStatus.PENDING

    }

    def "Created an order should be response with the correct details"() {
        given:
        cart = new Cart(UUID.randomUUID(), items)
        String recipientName = "Precious"
        String recipientAddress = "Lapu-lapu City"
        boolean canContainFood = false

        when:
        service.createAnOrder(cart, recipientName, recipientAddress, canContainFood)

        then:
        UnableToCreateOrderException failedOrder = thrown(UnableToCreateOrderException)
        failedOrder.message == "Cart should not contain FOOD items"
    }

}



