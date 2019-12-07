package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductPage extends Base {

    protected static String addToCartButtonLocator(){ return "//android.widget.Button[@resource-id='add-to-cart-button']"; }

    protected static String viewCartButtonLocator(){ return "//android.widget.ImageView[@content-desc='Cart']"; }

    protected static String proceedToBuyButtonLocator(){ return "//android.widget.Button[@text='Proceed to Buy']"; }

    protected static String selectedProductTitleLocator(){ return "//android.view.View[@resource-id='title_feature_div']/android.view.View";}

    protected static String selectedProductTitleInCartLocator(String product){ return "//android.widget.Image[contains(@text,'" + product + "')]";}

    protected static String selectedProductPriceInCartLocator(String price){ return "//android.view.View[contains(@text,'" + price + "')]";}

    protected static String selectedProductQuantityInCartLocator(){ return "//android.view.View[contains(@resource-id,'quantity-label')]/android.view.View";}

    protected static String selectedProductInstallationInCartLocator(){ return "//android.view.View[contains(@text, 'TV Installation Service')]";}

    protected static String selectedProductInstallationPriceInCartLocator(){ return "//android.view.View[contains(@text, '0.00')]";}


    public void clickOnAddToCart(){
        waitTimer(2000);
//        scrollToElementByText("TV Buying Guide");
        waitForVisibilityOfElement(By.xpath(addToCartButtonLocator()), 5);
        clickElementByXpath(addToCartButtonLocator());

    }

    public void clickToViewCart(){
        clickElementByXpath(viewCartButtonLocator());
        waitForVisibilityOfElement(By.xpath(proceedToBuyButtonLocator()), 15);
    }

    public void clickProceedToBuy() {
        scroll("up");
        waitTimer(2000);
        clickElementByXpath(proceedToBuyButtonLocator());
    }

    public String getProductTitleOnProductDetails(){
        return findElementByXpath(selectedProductTitleLocator()).getText();
    }

    public void verifyCorrectProductSelected(String product){
        Assert.assertEquals(product, getProductTitleOnProductDetails(), "Product title different from searched product");
    }

    public Set<String> pickCartQuantityValue(){
        Set<String> quantity_value = new HashSet<String>();
        List<WebElement> quanities = findElementsByXpath(selectedProductQuantityInCartLocator());
        for(WebElement quantity: quanities){
            quantity_value.add(quantity.getText());
        }
        return quantity_value;

    }

    public void verifyCartDetails(String product, String price) {
        scroll("down");

        // Verify Product added to Cart
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(selectedProductTitleInCartLocator(product)), 5).isDisplayed(),
                "Product title different from product added to cart");

        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(selectedProductPriceInCartLocator(price)), 5).isDisplayed(),
                "Product price different from product added to cart");

        Assert.assertTrue( pickCartQuantityValue().contains("1"),
                "Product quantity different added to cart");

        if(waitForVisibilityOfElement(By.xpath(selectedProductInstallationInCartLocator()),5) != null) {
            // Verify TV Installation
            Assert.assertTrue(waitForVisibilityOfElement(By.xpath(selectedProductInstallationInCartLocator()), 5).isDisplayed(),
                    "Product installation not provided");

            Assert.assertTrue(waitForVisibilityOfElement(By.xpath(selectedProductInstallationPriceInCartLocator()), 5).isDisplayed(),
                    "Product installation not provided");
        }
    }

}
