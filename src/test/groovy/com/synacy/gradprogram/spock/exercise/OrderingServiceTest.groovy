package com.synacy.gradprogram.spock.exercise


import spock.lang.Specification

class OrderingServiceTest extends Specification {

    OrderingService service
    Cart cart

    def items = [
            new Item("Chicken Cordon", 40, ItemType.FOOD),
            new Item("Trouser", 100, ItemType.CLOTHING),
            new Item("TV", 100, ItemType.APPLIANCE),
            new Item("Charger", 300, ItemType.GADGET),
            new Item("Pancit Malabon", 100, ItemType.FOOD)
    ]

    def setup() {
        service = new OrderingService(cart)
    }

    def "validateCartItem if there have food Order on the Cart"() {
        when:
        boolean validateCartItem = service.cartContainsFoodItem(cart = new Cart(UUID.randomUUID(), items))

        then:
        true == containsFoodItem
    }

    def "CalculateTotalCostOfCart should calculate the total cost of the items in the cart"() {
        given:
        def items = [
                new Item("Chicken Cordon", 40, ItemType.FOOD),
                new Item("Trouser", 100, ItemType.CLOTHING),

        ]
        when:
        double totalAmountPrice = service.calculateTotalCostOfCart(new Cart(UUID.randomUUID(), items))

        then:
        140d == totalAmountPrice

    }

    def "applyDiscountToCartItems is eligible for discount"() {
        given:
        cart = new Cart(UUID.randomUUID(), items)

        when:
        service.applyDiscountToCartItems(cart)

        then:
        eligibleDiscountItem
    }

    def "applyDiscountToCartItems Discount Eligible"() {
        given:
        cart = new Cart(UUID.randomUUID(), items)
        List totalDiscount = cart.getItems().collect() { it.getCost() }

        when:
        service.applyDiscountToCartItems(cart)
        List itemDiscounted = getItems().collect() { it.getCost() }

        then:
        result


    }

    def "createAnOrder if not food item thrown Exception"() {
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



