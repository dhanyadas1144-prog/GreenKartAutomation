package testcases;

import base.BaseTest;
import pages.HomePage;


import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePagePositive() {

        String url = driver.getCurrentUrl();

        Assert.assertTrue(
                url.contains("rahulshettyacademy"),
                "Home page is not loaded"
        );

        System.out.println("PASS: Home page loaded successfully");
    }


    @Test
    public void verifyHomePageTitle() {

        String title = driver.getTitle();

        System.out.println("Page Title: " + title);

        Assert.assertNotNull(title);
    }
   

    //footer
    @Test
    public void verifyFooterDisplayed() {

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
            homePage.isFooterDisplayed(),
            "Footer is not displayed on the home page."
        );
    }
   
   
    @Test
    public void verifyFooterContent() {

        HomePage homePage = new HomePage(driver);

        String footerText = homePage.getFooterText();

        Assert.assertFalse(
            footerText.trim().isEmpty(),
            "Footer content is empty."
        );

        System.out.println("Footer content: " + footerText);
    }
}
