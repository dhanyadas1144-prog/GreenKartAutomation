package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    By cartProducts =
            By.cssSelector("p.product-name");

    By quantity =
            By.cssSelector("input.quantity");

   // By totalPrice =
            //By.cssSelector("span.totamt");

    By proceedButton =
            By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]");


    // Get products from cart

    public List<WebElement> getCartProducts() {

        return driver.findElements(cartProducts);
    }


    // Get quantity

    public String getQuantity() {

        return driver.findElement(quantity)
                     .getAttribute("value");
    }


    // Get total price
    private By proceedToCheckout = By.xpath("//button[text()='PROCEED TO CHECKOUT']");
    private By totalPrice = By.cssSelector("span.totAmt");  
    public String getTotalPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckout)).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice)).getText();
    }

    // Proceed to checkout

    public void proceedToCheckout() {

        driver.findElement(proceedButton).click();
    }
}

