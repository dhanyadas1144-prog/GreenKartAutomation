package E2EWorkflowTest;



import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class E2EWorkflowTest extends BaseTest {

    private static final String PRODUCT    = "Brocolli";
    private static final String PROMO_CODE = "rahulshettyacademy";
    private static final String COUNTRY    = "India";

    // ------------------------------------------------------------------
    // POSITIVE: Complete Purchase Workflow
    // Home -> Search -> Add to cart -> Cart -> Checkout -> Promo
    //      -> Place order -> Country + Terms -> Order confirmation
    // ------------------------------------------------------------------
    @Test(priority = 1)
    public void completePurchaseWorkflow() {

        E2EWorkflowPage flow = new E2EWorkflowPage(driver);

        // Home page + product search
        Assert.assertTrue(driver.getCurrentUrl().contains("rahulshettyacademy"),
                "Home page is not loaded");
        flow.homePage.searchProduct(PRODUCT);
        Assert.assertTrue(flow.homePage.isProductDisplayed(),
                "Searched product is not displayed");

        // Add to cart
        flow.homePage.addProduct(PRODUCT);
        flow.openCart();

        // Verify cart
        List<WebElement> cartProducts = flow.getCartProducts();
        Assert.assertTrue(cartProducts.size() > 0, "Product was not added to cart");
        Assert.assertEquals(flow.getCartQuantity(), "1", "Incorrect cart quantity");

        // Checkout + promo code
        flow.proceedToCheckout();
        String promoMessage = flow.applyPromoCode(PROMO_CODE);
        Assert.assertTrue(promoMessage.contains("Code applied"),
                "Valid promo code was not applied");

        // Place order -> country + terms
        flow.placeOrder();
        flow.selectCountry(COUNTRY);
        flow.acceptTerms();
        flow.clickProceed();

        // Confirmation
        String success = flow.getOrderSuccessMessage();
        Assert.assertTrue(success.contains("Thank you"),
                "Order was not placed successfully");
        System.out.println("PASS: E2E purchase completed - " + success);
    }

    // ------------------------------------------------------------------
    // NEGATIVE: Empty Cart Checkout
    // ------------------------------------------------------------------
    @Test(priority = 2)
    public void emptyCartCheckout() {

        E2EWorkflowPage flow = new E2EWorkflowPage(driver);

        // Open the cart without adding anything
        flow.openCart();

        Assert.assertTrue(flow.isCartEmptyMessageDisplayed(),
                "Empty cart message is not displayed");
        Assert.assertEquals(flow.getCartProducts().size(), 0,
                "Cart should contain no products");

        // Try to proceed to checkout anyway
     // Try to proceed to checkout anyway
        try {
            flow.proceedToCheckout();
        } catch (Exception e) {
            // Button missing/disabled is also acceptable for an empty cart
        }

        // The site allows reaching checkout, so verify there is nothing to buy
        Assert.assertEquals(flow.getCartProducts().size(), 0,
                "Checkout page must not contain any products for an empty cart");
        System.out.println("PASS: No products available to purchase with an empty cart");
    }

    // ------------------------------------------------------------------
    // NEGATIVE: Missing Mandatory Fields (terms & conditions not accepted)
    // ------------------------------------------------------------------
    @Test(priority = 3)
    public void missingMandatoryFields() {

        E2EWorkflowPage flow = new E2EWorkflowPage(driver);

        flow.searchAndAddProduct(PRODUCT);
        flow.openCart();
        flow.proceedToCheckout();
        flow.placeOrder();

        // Select country but DO NOT accept the terms checkbox
        flow.selectCountry(COUNTRY);
        flow.clickProceed();

        String error = flow.getTermsErrorMessage();
        Assert.assertTrue(error.contains("Terms"),
                "Validation message for mandatory terms is missing");
        System.out.println("PASS: Order blocked - " + error);
    }
}

