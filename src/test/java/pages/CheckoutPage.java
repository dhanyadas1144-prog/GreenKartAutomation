package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;   // <-- was null before
    }

    By promoCode   = By.cssSelector("input.promoCode");
    By applyButton = By.cssSelector("button.promoBtn");
    By promoMessage = By.cssSelector("span.promoInfo");
    By placeOrder = By.xpath(
        "//button[contains(translate(normalize-space(.),"
      + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'place order')]");

    public void enterPromoCode(String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(promoCode));
        driver.findElement(promoCode).sendKeys(code);
    }

    public void applyPromoCode() {
        driver.findElement(applyButton).click();
    }

    public String getPromoMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(promoMessage));
        return driver.findElement(promoMessage).getText();
    }

    public void placeOrder() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
        js.executeScript("arguments[0].scrollIntoView(true);", btn);
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", btn);
        }
    }
}