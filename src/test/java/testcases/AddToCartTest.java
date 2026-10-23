package testcases;

import base.BaseTest;
import pages.HomePage;
import pages.CartPage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddToCartTest extends BaseTest {

    @Test
    public void addValidProductToCart() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.searchProduct("Brocolli");

        homePage.addProduct("Brocolli");

        homePage.openCart();

        List<WebElement> products =
                cartPage.getCartProducts();

        Assert.assertTrue(
                products.size() > 0,
                "Product was not added to cart"
        );

        System.out.println(
                "PASS: Product added to cart"
        );
    }


    @Test
    public void verifyProductNotAddedWhenInvalid() {

        HomePage homePage = new HomePage(driver);

        homePage.searchProduct("XYZ12345");

        List<WebElement> products =
                homePage.getProducts();

        Assert.assertEquals(
                products.size(),
                0,
                "Invalid product should not be available"
        );

        System.out.println(
                "PASS: Invalid product cannot be added"
        );
    }
}
