Feature: to test website functionality

  Scenario: Test to validate user is able to add any item to card and checkout the item
    Given Go to the Website.
    And Navigate to Computer section
    And Click on Notebooks
    And Sort the items by Price to maximum
    And Get the results listed with Name and Price
    And Add  item to the cart
    And Go to shopping cart page from Nav bar and verify item is added to cart
    When Click on check Out button on Shopping cart Page
    And On SignIn page click on Register and register with any random email address
    And Navigate to Shopping Cart button increase the order Quantity to four
    And Fill up the shipping details
    Then Verify that your order is placed after filling all the details