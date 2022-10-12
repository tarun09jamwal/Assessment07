package StepDefinition;

import Pages.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;

public class BuyProduct extends BaseClass {
    @Given("Go to the Website.")
    public void go_to_the_website() throws IOException {
        Setup();
        pageFactory.getProductBuy().WebsiteURL();
    }

    @Given("Navigate to Computer section")
    public void navigate_to_computer_section() {
        pageFactory.getProductBuy().Computer();
    }

    @Given("Click on Notebooks")
    public void click_on_notebooks() {
        pageFactory.getProductBuy().Categories();
    }

    @Given("Sort the items by Price to maximum")
    public void sort_the_items_by_price_to_maximum() {
        pageFactory.getProductBuy().Sort();

    }

    @Given("Get the results listed with Name and Price")
    public void get_the_results_listed_with_name_and_price() {
        pageFactory.getProductBuy().ProductDetails();

    }

    @Given("Add  item to the cart")
    public void add_item_to_the_cart() {
        pageFactory.getProductBuy().VerifyMessage();

    }

    @Given("Go to shopping cart page from Nav bar and verify item is added to cart")
    public void go_to_shopping_cart_page_from_nav_bar_and_verify_item_is_added_to_cart() {
        pageFactory.getProductBuy().ShoppingCart("Shopping cart");
    }

    @When("Click on check Out button on Shopping cart Page")
    public void click_on_check_out_button_on_shopping_cart_page() {
        pageFactory.getProductBuy().Checkout();

    }

    @When("On SignIn page click on Register and register with any random email address")
    public void on_sign_in_page_click_on_register_and_register_with_any_random_email_address() throws IOException {
        pageFactory.getProductBuy().RegisterUser();


    }

    @When("Navigate to Shopping Cart button increase the order Quantity to four")
    public void navigate_to_shopping_cart_button_increase_the_order_quantity_to_four() throws IOException {
        pageFactory.getProductBuy().ProductQuantity("Shopping cart");
    }

    @When("Fill up the shipping details")
    public void fill_up_the_shipping_details() throws IOException {
        pageFactory.getProductBuy().ShippingDetails();

    }

    @Then("Verify that your order is placed after filling all the details")
    public void verify_that_your_order_is_placed_after_filling_all_the_details() {
        pageFactory.getProductBuy().VerifyOrder();

    }

}
