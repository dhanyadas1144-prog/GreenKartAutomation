
package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckoutTest extends BaseTest {

    // Positive Test Case: Valid Promo Code
    @Test
    public void validPromoCodeTest() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Step 1: Search for product
        homePage.searchProduct("Brocolli");

        // Step 2: Add product to cart
        homePage.addProduct("Brocolli");

        // Step 3: Open cart
        homePage.openCart();

        // Step 4: Proceed to checkout
        cartPage.proceedToCheckout();

        // Step 5: Enter valid promo code
        checkoutPage.enterPromoCode("rahulshettyacademy");

        // Step 6: Apply promo code
        checkoutPage.applyPromoCode();

        // Step 7: Get promo message
        String message = checkoutPage.getPromoMessage();

        System.out.println("Valid Promo Message: " + message);

        // Step 8: Validate result
        Assert.assertTrue(
            message.toLowerCase().contains("code applied"),
            "Valid promo code was not applied. Actual message: " + message
        );
    }

    // Negative Test Case: Invalid Promo Code
    @Test
    public void invalidPromoCodeTest() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // Step 1: Search for product
        homePage.searchProduct("Brocolli");

        // Step 2: Add product to cart
        homePage.addProduct("Brocolli");

        // Step 3: Open cart
        homePage.openCart();

        // Step 4: Proceed to checkout
        cartPage.proceedToCheckout();

        // Step 5: Enter invalid promo code
        checkoutPage.enterPromoCode("INVALID123");

        // Step 6: Apply promo code
        checkoutPage.applyPromoCode();

        // Step 7: Get promo message
        String message = checkoutPage.getPromoMessage();

        System.out.println("Invalid Promo Message: " + message);

        // Step 8: Validate result
        Assert.assertFalse(
            message.toLowerCase().contains("code applied"),
            "Invalid promo code should not be applied. Actual message: " + message
        );
    }
}
