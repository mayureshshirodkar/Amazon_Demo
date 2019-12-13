package amazon.pages;

import amazon.base.Base;
import amazon.utilities.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

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
            clickElementByXpath(languageRadioLocator, homePageLogger, "User clicks on language radio button");
            clickElementByXpath(saveLanguageChangeLocator, homePageLogger, "User clicks on save language preference button");
        }
        else{
            Utils.passStep(homePageLogger, "No Language Popup Displayed to user");
        }
    }

    /**
     * Tap on home and Navigate to Home page
     */
    public void navigateToHome(){
        clickElementByXpath(homeButtonLocator, homePageLogger, "User clicks on home button");
    }


    /**
     * Delete all products added to cart
     */
    public void deleteProductsAddedToCart(){
        product_page.clickToViewCart();
        swipeInDirection("down");
        waitTimer(1);
        clickElementsByXpath(deleteProductLocator);
        assertIfTrue(emptyCartMessageLocator, "Assert if user has removed all products from cart");
    }

}
