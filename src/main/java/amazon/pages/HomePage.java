package amazon.pages;

import amazon.base.Base;
import amazon.utilities.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage extends Base {

    private ProductPage product_page = new ProductPage();
    private static Logger homePageLogger = LogManager.getLogger(HomePage.class);

    private String saveLanguageChangeLocator = "//*[@text='Save Changes']";

    private String languageRadioLocator = "//*[@text='English - EN']";

    private String homeButtonLocator = "//*[@content-desc='Home']";

    private String deleteProductLocator = "//*[@text='Delete']";

    private String emptyCartMessageLocator = "//*[contains(@text,'Your Shopping Cart is empty.')]";


    /**
     * Accept the app language selection popup
     */
    public void acceptLanguagePopup(){
        if (isElementDisplayed(languageRadioLocator)) {
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
        clickElementsByXpath(deleteProductLocator, homePageLogger, "User clicks on delete products");
        assertIfElementPresent(emptyCartMessageLocator, "Assert if user has removed all products from cart");
    }

}
