package testcases;

import base.BaseTest;
import pages.HomePage;
import pages.CartPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void verifyCartQuantity() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.searchProduct("Brocolli");

        homePage.addProduct("Brocolli");

        homePage.openCart();

        String quantity =
                cartPage.getQuantity();

        Assert.assertEquals(
                quantity,
                "1",
                "Incorrect quantity"
        );

        System.out.println(
                "PASS: Cart quantity is 1"
        );
    }


    @Test
    public void verifyCartPrice() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.searchProduct("Brocolli");

        homePage.addProduct("Brocolli");

        homePage.openCart();

        String price =
                cartPage.getTotalPrice();

        Assert.assertFalse(
                price.isEmpty(),
                "Cart price is not displayed"
        );

        System.out.println(
                "Total Price: " + price
        );
    }
}
