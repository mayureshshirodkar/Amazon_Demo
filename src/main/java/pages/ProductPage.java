package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductPage extends Base {

    private static String addToCartButtonLocator = "//android.widget.Button[@resource-id='add-to-cart-button']";

    protected static String viewCartButtonLocator = "//android.widget.ImageView[@content-desc='Cart']";

    private static String proceedToBuyButtonLocator = "//android.widget.Button[@text='Proceed to Buy']";

    private static String selectedProductTitleLocator = "//android.view.View[@resource-id='title_feature_div']/android.view.View";

    private static String selectedProductTitleInCartLocator(String product){ return "//android.widget.Image[contains(@text,'" + product + "')]";}

    private static String selectedProductPriceInCartLocator(String price){ return "//android.view.View[contains(@text,'" + price + "')]";}

    private static String selectedProductQuantityInCartLocator = "//android.view.View[contains(@resource-id,'quantity-label')]/android.view.View";

    private static String selectedProductInstallationInCartLocator = "//android.view.View[contains(@text, 'TV Installation Service')]";

    private static String selectedProductInstallationPriceInCartLocator = "//android.view.View[contains(@text, '0.00')]";


    /**
     * Click on the add to cart button
     */
    public void clickOnAddToCart(){
        waitTimer(2);
        scrollToElementByText("Add to Cart");
        waitForVisibilityOfElement(By.xpath(addToCartButtonLocator), 5);
        clickElementByXpath(addToCartButtonLocator);

    }

    /**
     * Click on the view cart button
     */
    public void clickToViewCart(){
        clickElementByXpath(viewCartButtonLocator);
        waitForVisibilityOfElement(By.xpath(proceedToBuyButtonLocator), 15);
    }


    /**
     * Scroll to and Click on the proceed to buy button
     */
    public void clickProceedToBuy() {
        swipeInDirection("up");
        waitTimer(2);
        clickElementByXpath(proceedToBuyButtonLocator);
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
        System.out.println("Correct product selected and displayed!!");
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

        System.out.println("Cart details verified!!");
    }

}
