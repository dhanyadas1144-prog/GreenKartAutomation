
package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class ProductSearchTest extends BaseTest {

    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        return new Object[][] {
            {"Brocolli", true},  // Valid product
            {"Carrot", true},
            {"Cucumber", true},
            {"XYZ123", false} ,   // Invalid product
            {"fsdfsdf", false}
        };
    }

    @Test(dataProvider = "searchData")
    public void verifyProductSearch(
            String productName,
            boolean expectedResult) {

        HomePage homePage = new HomePage(driver);

        homePage.searchProduct(productName);

        boolean actualResult = homePage.isProductDisplayed();

        Assert.assertEquals(
            actualResult,
            expectedResult,
            "Search validation failed for: " + productName
        );

        System.out.println(
            "Search input: '" + productName
            + "' | Expected: " + expectedResult
            + " | Actual: " + actualResult
        );
    }
}
