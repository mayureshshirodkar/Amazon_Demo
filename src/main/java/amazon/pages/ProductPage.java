package amazon.pages;

import amazon.base.Base;
import amazon.utilities.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductPage extends Base {

    private static String addToCartButtonLocator = "//*[@resource-id='add-to-cart-button']";

    protected static String viewCartButtonLocator = "//*[@content-desc='Cart']";

    private static String proceedToBuyButtonLocator = "//*[@text='Proceed to Buy']";

    private static String selectedProductTitleLocator = "//android.view.View[@resource-id='title_feature_div']/android.view.View";

    private static String selectedProductTitleInCartLocator(String product){ return "//android.widget.Image[contains(@text,'" + product + "')]";}

    private static String selectedProductPriceInCartLocator(String price){ return "//android.view.View[contains(@text,'" + price + "')]";}

    private static String selectedProductQuantityInCartLocator = "//*[contains(@resource-id,'quantity-label')]/android.view.View";

    private static String selectedProductInstallationInCartLocator = "//*[contains(@text, 'TV Installation Service')]";

    private static String selectedProductInstallationPriceInCartLocator = "//*[contains(@text, '0.00')]";

    private static Logger productPageLogger = LogManager.getLogger(ProductPage.class);


    /**
     * Click on the add to cart button
     */
    public void clickOnAddToCart(){
        waitTimer(2);
        scrollToElementByText("Add to Cart");
        waitForVisibilityOfElement(By.xpath(addToCartButtonLocator), 5);
        clickElementByXpath(addToCartButtonLocator);
        Utils.debugLog(productPageLogger, "Clicked on add to cart");

    }

    /**
     * Click on the view cart button
     */
    public void clickToViewCart(){
        clickElementByXpath(viewCartButtonLocator);
        waitForVisibilityOfElement(By.xpath(proceedToBuyButtonLocator), 15);
        Utils.debugLog(productPageLogger, "Clicked on view cart");
    }


    /**
     * Scroll to and Click on the proceed to buy button
     */
    public void clickProceedToBuy() {
        swipeInDirection("up");
        waitTimer(2);
        clickElementByXpath(proceedToBuyButtonLocator);
        Utils.debugLog(productPageLogger, "Clicked on proceed to buy");
    }

    /**
     * Extract the product title from product details
     */
    public String getProductTitleOnProductDetails(){
        return findElementByXpath(selectedProductTitleLocator).getText();
    }

    /**
     * Assert to verify correct product is selected
     */
    public void verifyCorrectProductSelected(String product){
        Assert.assertEquals(product, getProductTitleOnProductDetails(), "Product title different from searched product");
        Utils.debugLog(productPageLogger, "Correct product selected and displayed!!");
    }

    /**
     * Check the quantity of items in the cart
     */
    public Set<String> pickCartQuantityValue(){
        Set<String> quantity_value = new HashSet<String>();
        List<WebElement> quanities = findElementsByXpath(selectedProductQuantityInCartLocator);
        for(WebElement quantity: quanities){
            quantity_value.add(quantity.getText());
        }
        return quantity_value;

    }


    /**
     * Verify the cart details as compared to the search page
     */
    public void verifyCartDetails(String product, String price) {
        swipeInDirection("down");

        // Verify Product added to Cart
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(selectedProductTitleInCartLocator(product)), 5).isDisplayed(),
                "Product title different from product added to cart");

        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(selectedProductPriceInCartLocator(price)), 5).isDisplayed(),
                "Product price different from product added to cart");

        Assert.assertTrue( pickCartQuantityValue().contains("1"),
                "Product quantity different added to cart");

        if(waitForVisibilityOfElement(By.xpath(selectedProductInstallationInCartLocator),5) != null) {
            // Verify TV Installation
            Assert.assertTrue(waitForVisibilityOfElement(By.xpath(selectedProductInstallationInCartLocator), 5).isDisplayed(),
                    "Product installation not provided");

            Assert.assertTrue(waitForVisibilityOfElement(By.xpath(selectedProductInstallationPriceInCartLocator), 5).isDisplayed(),
                    "Product installation not provided");
        }

        Utils.debugLog(productPageLogger, "Cart details verified!!");
    }

}
