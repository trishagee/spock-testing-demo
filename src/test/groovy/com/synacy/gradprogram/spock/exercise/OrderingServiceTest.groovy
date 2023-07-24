package com.synacy.gradprogram.spock.exercise

import spock.lang.Specification

class OrderingServiceTest extends Specification {


    OrderingService service
    OrderRepository orderRepository = new OrderRepository()

    def items = [
            new Item("Chicken Cordon", 40, ItemType.FOOD),
            new Item("Trouser", 100, ItemType.CLOTHING),
            new Item("TV", 100, ItemType.APPLIANCE),
            new Item("Charger", 300, ItemType.GADGET),
            new Item("Pancit Malabon", 100, ItemType.FOOD),
            new Item("Headset", 500, ItemType.GADGET)
    ]
    Cart itemsInTheCart = new Cart(UUID.randomUUID(), items)

    def setup() {
        service = new OrderingService(orderRepository)
    }

    def "validateCartItem Should Validate the Cart Items if there food order in the Cart"() {
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

        when:
        boolean containsFoodItem = service.cartContainsFoodItem(itemsInTheCart)

        then:
        containsFoodItem

    }

    def "CalculateTotalCostOfCart should calculate the total cost of the items in the cart"() {
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
        when:
        double totalAmountPrice = service.calculateTotalCostOfCart(itemsInTheCart)

        then:
        1140d == totalAmountPrice
    }

    def "isCartEligibleForDiscount Should Validate the Cart if it is Eligible for discount "() {
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
        when:
        boolean eligibleForDiscount = service.isCartEligibleForDiscount(itemsInTheCart)

        then:
        eligibleForDiscount
    }

    def "applyDiscountToCartItems Should apply discount when cart is eligible for discount"() {
        given:
        def items = [
                new Item("Chicken Cordon", 200, ItemType.FOOD),
                new Item("Trouser", 100, ItemType.CLOTHING),
                new Item("TV", 100, ItemType.APPLIANCE),
                new Item("Charger", 300, ItemType.GADGET),
                new Item("Pancit Malabon", 100, ItemType.FOOD),
                new Item("Headset", 500, ItemType.GADGET)
        ]

        Cart itemsInTheCart = new Cart(UUID.randomUUID(), items)

        when:
        service.applyDiscountToCartItems(itemsInTheCart)

        then:
        20d == items[0].getCost()
        10d == items[1].getCost()
        10d == items[2].getCost()
        30d == items[3].getCost()
        10d == items[4].getCost()
        50d == items[5].getCost()
    }

    def "applyDiscountToCartItems Should not apply discount when the cart is not eligible for discount"() {

        def items = [
                new Item("Chicken Cordon", 200, ItemType.FOOD),
                new Item("Trouser", 100, ItemType.CLOTHING),
                new Item("TV", 100, ItemType.APPLIANCE),
                new Item("Charger", 300, ItemType.GADGET),
        ]

        Cart itemsInTheCart = new Cart(UUID.randomUUID(), items)

        when:
        service.applyDiscountToCartItems(itemsInTheCart)

        then:
        200d == items.get(0).getCost()
        100d == items.get(1).getCost()
        100d == items.get(2).getCost()
        300d == items.get(3).getCost()
    }


    def "createAnOrder Should create an Order with the correct details with food contain"() {
        given:
        def items = [
                new Item("Chicken Cordon", 200, ItemType.FOOD),
                new Item("Trouser", 100, ItemType.CLOTHING),
                new Item("TV", 100, ItemType.APPLIANCE),
                new Item("Charger", 300, ItemType.GADGET),
                new Item("Pancit Malabon", 100, ItemType.FOOD),
                new Item("Headset", 500, ItemType.GADGET)
        ]

        itemsInTheCart = new Cart(UUID.randomUUID(), items)

        double totalCostOfCart = 1300
        String recipientName = "Precious"
        String recipientAddress = "Lapu-lapu City"
        OrderStatus orderStatus = OrderStatus.PENDING
        boolean canContainFood = true


        when:
        Order order = service.createAnOrder(itemsInTheCart, recipientName, recipientAddress, canContainFood)

        then:
        order.recipientName == recipientName
        order.recipientAddress == recipientAddress
        order.status == orderStatus
        order.totalCost == totalCostOfCart
    }

    def "createAnOrder Should not create an order if no Food item "() {
        given:
        def items = [
                new Item("Chicken Cordon", 200, ItemType.FOOD),
                new Item("Trouser", 100, ItemType.CLOTHING),
                new Item("TV", 100, ItemType.APPLIANCE),
                new Item("Charger", 300, ItemType.GADGET),
                new Item("Pancit Malabon", 100, ItemType.FOOD),
                new Item("Headset", 500, ItemType.GADGET)
        ]

        Cart itemsInTheCart = new Cart(UUID.randomUUID(), items)
        String recipientName = "Precious"
        String recipientAddress = "Lapu-lapu City"
        boolean canContainFood = false

        when:
        service.createAnOrder(itemsInTheCart, recipientName, recipientAddress, canContainFood)
        then:
        UnableToCreateOrderException exception = thrown(UnableToCreateOrderException)
        exception.message == "Cart should not contain FOOD items"

    }
}
