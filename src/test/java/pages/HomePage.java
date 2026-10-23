package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    By searchBox =
            By.cssSelector("input.search-keyword");

    By searchButton =
            By.cssSelector("button.search-button");

    By productNames =
            By.cssSelector("h4.product-name");

    By addToCartButtons =
            By.xpath("//div[@class='product-action']/button");

    By cartIcon =
            By.cssSelector("img[alt='Cart']");
   

    // Search product

    public void searchProduct1(String productName) {

        driver.findElement(searchBox)
              .sendKeys(productName);

        driver.findElement(searchButton)
              .click();
    }

    
    
//E2E
    

public void addProductToCart() {
    driver.findElement(
        By.xpath("//div[@class='product-action']/button")
    ).click();
}

public void openCart1() {
    driver.findElement(
        By.cssSelector("img[alt='Cart']")
    ).click();
}

    // Get product names

    public List<WebElement> getProducts() {

        return driver.findElements(productNames);
    }


    // Add product to cart

    public void addProduct(String productName) {

        List<WebElement> products =
                driver.findElements(productNames);

        List<WebElement> buttons =
                driver.findElements(addToCartButtons);

        for (int i = 0; i < products.size(); i++) {

            String name =
                    products.get(i).getText();

            String actualName =
                    name.split("-")[0].trim();

            if (actualName.equalsIgnoreCase(productName)) {

                buttons.get(i).click();

                break;
            }
        }
    }


    // Open cart

    public void openCart() {

        driver.findElement(cartIcon).click();
    }
        //Footer
        By footer = By.tagName("footer");
        public boolean isFooterDisplayed() {
            WebElement footerElement = driver.findElement(footer);
            return footerElement.isDisplayed();
        }

        public String getFooterText() {
            return driver.findElement(footer).getText();
        }
 

        public void searchProduct(String productName) {
            driver.findElement(searchBox).clear();
            driver.findElement(searchBox).sendKeys(productName);
            driver.findElement(searchButton).click();
        }

public boolean isProductDisplayed() {
    return driver.findElements(productNames).size() > 0;
}
   
}

