package E2EWorkflowTest;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;

/**
 * Orchestrates the full GreenKart journey by re-using the existing
 * HomePage, CartPage and CheckoutPage classes, and adds the final
 * "country / terms" order page that none of them cover.
 */
public class E2EWorkflowPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Existing page objects (re-used, not modified)
    public final HomePage homePage;
    public final CartPage cartPage;
    public final CheckoutPage checkoutPage;

    // ---------- Locators for pages not covered by existing classes ----------
    private final By emptyCartMessage = By.cssSelector("div.empty-cart");
    private final By countryDropdown  = By.cssSelector("div.wrapperTwo select");
    private final By termsCheckbox    = By.cssSelector("input.chkAgree");
    private final By proceedButton    = By.xpath("//button[normalize-space()='Proceed']");
    private final By termsError       = By.xpath("//*[contains(text(),'Please accept Terms')]");
    private final By successMessage   = By.xpath("//span[contains(text(),'Thank you')]");
    private final By placeOrderButton = By.xpath("//button[contains(text(),'Place Order')]");

    public E2EWorkflowPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.homePage = new HomePage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    // ---------- Workflow steps (use existing classes) ----------

    public void searchAndAddProduct(String productName) {
        homePage.searchProduct(productName);
        homePage.addProduct(productName);
    }

    public void openCart() {
        homePage.openCart();
    }

    public List<WebElement> getCartProducts() {
        return cartPage.getCartProducts();
    }

    public String getCartQuantity() {
        return cartPage.getQuantity();
    }

    public void proceedToCheckout() {
        cartPage.proceedToCheckout();
    }

    public String applyPromoCode(String code) {
        checkoutPage.enterPromoCode(code);
        checkoutPage.applyPromoCode();
        return checkoutPage.getPromoMessage();
    }

    public void placeOrder() {
        checkoutPage.placeOrder();
    }

    // ---------- Order page (country + terms) ----------

    public void selectCountry(String country) {
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(countryDropdown));
        new Select(dropdown).selectByVisibleText(country);
    }

    public void acceptTerms() {
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(termsCheckbox));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void clickProceed() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedButton)).click();
    }

    // ---------- Verifications ----------

    public boolean isCartEmptyMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions
                    .visibilityOfElementLocated(emptyCartMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** True if the checkout (Place Order) page was reached. */
    public boolean isCheckoutPageDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTermsErrorMessage() {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(termsError)).getText();
    }

    public String getOrderSuccessMessage() {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(successMessage)).getText();
    }
}

