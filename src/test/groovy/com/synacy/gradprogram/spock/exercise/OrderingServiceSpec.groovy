package com.synacy.gradprogram.spock.exercise

import spock.lang.Specification
import spock.lang.Unroll

class OrderingServiceSpec extends Specification {

    def setup(){
    }

    OrderingService orderingService =  new OrderingService()
    Item food = ["Burger", 15, ItemType.FOOD]
    Item food2 = ["Pizza", 5, ItemType.FOOD]
    Item gadget = ["Cellphone", 10, ItemType.GADGET]
    Item gadget2 = ["iPad", 5, ItemType.GADGET]
    Item appliance = ["TV", 20, ItemType.APPLIANCE]
    Item appliance2 = ["TV", 5, ItemType.APPLIANCE]
    Item clothing = ["Dress", 10, ItemType.CLOTHING]
    Item clothing2 = ["Dress", 5, ItemType.CLOTHING]



    def "cartContainsFoodItem should respond with true if cart contains item with type food"() {
        given:
        List<Item> testListItemWFood = [food, gadget, appliance, clothing]
        def testCart = new Cart(UUID.randomUUID(), testListItemWFood)

        when:
        boolean foodItem = orderingService.cartContainsFoodItem(testCart)

        then:
        foodItem
    }

    def "cartContainsFoodItem should respond with false if cart does not contains item with type food"() {
        given:
        List<Item> testListItemWoFood = [gadget, appliance, clothing]
        def testCart = new Cart(UUID.randomUUID(), testListItemWoFood)

        when:
        boolean foodItem = orderingService.cartContainsFoodItem(testCart)

        then:
        !foodItem
    }

    def "calculateTotalCostOfCart should respond with the total cost of item inside the cart"() {
        given:
        List<Item> testListItem = [food, gadget, appliance, clothing]
        def testCart = new Cart(UUID.randomUUID(), testListItem)

        when:
        double testTotal = orderingService.calculateTotalCostOfCart(testCart)

        then:
        testTotal == 55
    }

    def "isCartEligibleForDiscount should respond with true if total cart price is greater than 50.0 and size is greater than 5"() {

        given:
        List<Item> testListItem = [food, gadget, appliance, clothing, food2, gadget2]
        def testCart = new Cart(UUID.randomUUID(), testListItem)

        when:
        boolean isEligible = orderingService.isCartEligibleForDiscount(testCart)

        then:
        isEligible
    }

    def "isCartEligibleForDiscount should respond with false if cart price is less than 50.0 and cart size is less than 5"() {

        given:
        List<Item> testListItem = [food, gadget, clothing]
        def testCart = new Cart(UUID.randomUUID(), testListItem)

        when:
        boolean isEligible = orderingService.isCartEligibleForDiscount(testCart)

        then:
        !isEligible
    }

    def "isCartEligibleForDiscount should respond with false if cart price is greater than 50.0 but cart size is less than 5"() {

        given:
        List<Item> testListItem = [food, gadget, appliance, clothing]
        def testCart = new Cart(UUID.randomUUID(), testListItem)

        when:
        boolean isEligible = orderingService.isCartEligibleForDiscount(testCart)

        then:
        !isEligible
    }

    def "isCartEligibleForDiscount should respond with false if cart price is less than 50.0 but cart size is greater than 5"() {

        given:
        List<Item> testListItem = [food2, gadget2, clothing2, gadget, clothing, appliance2]
        def testCart = new Cart(UUID.randomUUID(), testListItem)

        when:
        boolean isEligible = orderingService.isCartEligibleForDiscount(testCart)

        then:
        !isEligible
    }

    @Unroll
    def "ApplyDiscountToCartItems should respond with discounted cost"() {
        given:
        List<Item> testListItem = [food, gadget, appliance, clothing, food2, gadget2]
        def testCart = new Cart(UUID.randomUUID(), testListItem)

        when:
        orderingService.applyDiscountToCartItems(testCart)

        then:
        testCart.items.cost << [1.5,1,2,1,0.5,0.5]
    }

    def "CreateAnOrder should throw exception when canContainFood is true and cart has no food items"() {
        given:
        List<Item> testListItem = [gadget, appliance, clothing, gadget2, food]
        def testCart = new Cart(UUID.randomUUID(), testListItem)
        def recipientName = "Harry"
        def recipientAddress = "Cebu City"
        def canContainFood = false

        when:
        def testOrder = orderingService.createAnOrder(testCart, recipientName, recipientAddress, canContainFood)

        then:
        UnableToCreateOrderException orderException = thrown(UnableToCreateOrderException)
        orderException.message == "Cart should not contain FOOD items"
    }

    def "CreateAnOrder should save new order and set order status to Pending"() {
        given:
        List<Item> testListItem = [gadget, appliance, clothing, gadget2]
        def testCart = new Cart(UUID.randomUUID(), testListItem)
        def recipientName = "Harry"
        def recipientAddress = "Cebu City"
        def canContainFood = true

        when:
        def testOrder = orderingService.createAnOrder(testCart, recipientName, recipientAddress, canContainFood)

        then:
        testOrder.totalCost == orderingService.calculateTotalCostOfCart(testCart)
        testOrder.recipientName == recipientName
        testOrder.recipientAddress == recipientAddress
        testOrder.status== OrderStatus.PENDING
    }
}
