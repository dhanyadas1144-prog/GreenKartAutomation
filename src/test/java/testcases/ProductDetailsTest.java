package testcases;

import base.BaseTest;
import pages.HomePage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductDetailsTest extends BaseTest {

    @Test
    public void verifyProductDetails() {

        HomePage homePage = new HomePage(driver);

        homePage.searchProduct("Brocolli");

        List<WebElement> products =
                homePage.getProducts();

        Assert.assertTrue(
                products.size() > 0,
                "Product not found"
        );

        String productName =
                products.get(0).getText();

        Assert.assertTrue(
                productName.contains("Brocolli"),
                "Incorrect product displayed"
        );

        System.out.println(
                "Product: " + productName
        );
    }
}
