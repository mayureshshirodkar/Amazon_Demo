package amazon.pages;

import amazon.base.Base;
import amazon.utilities.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomePage extends Base {

    private ProductPage product_page = new ProductPage();

    private String saveLanguageChangeLocator = "//*[@text='Save Changes']";

    private String languageRadioLocator = "//*[@text='English - EN']";

    private String homeButtonLocator = "//*[@content-desc='Home']";

    private String deleteProductLocator = "//*[@text='Delete']";

    private String emptyCartMessageLocator = "//*[contains(@text,'Your Shopping Cart is empty.')]";

    private static Logger homePageLogger = LogManager.getLogger(HomePage.class);

    /**
     * Accept the app language selection popup
     */
    public void acceptLanguagePopup(){
        if(waitForVisibilityOfElement(By.xpath(languageRadioLocator), 15) != null) {
            clickElementByXpath(languageRadioLocator);
            clickElementByXpath(saveLanguageChangeLocator);
            Utils.debugLog(homePageLogger, "Language Popup Accepted");
        }
        else{
            Utils.debugLog(homePageLogger, "No Language Popup Displayed");
        }
    }

    /**
     * Tap on home and Navigate to Home page
     */
    public void navigateToHome(){
        clickElementByXpath(homeButtonLocator);
        Utils.debugLog(homePageLogger, "Navigate to Home page");
    }


    /**
     * Delete all products added to cart
     */
    public void deleteProductsAddedToCart(){
        product_page.clickToViewCart();
        swipeInDirection("down");
        waitTimer(1);
        List<WebElement> products = findElementsByXpath(deleteProductLocator);
        for(WebElement product:products){
            product.click();
        }
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(emptyCartMessageLocator), 10).isDisplayed(),
                "Assertion Failed! Payment option not shown");
        Utils.debugLog(homePageLogger, "Products removed from cart!!");
    }

}
