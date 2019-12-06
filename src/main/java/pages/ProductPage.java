package pages;

import base.Base;
import org.openqa.selenium.By;

public class ProductPage extends Base {

    protected static String addToCartButtonLocator(){ return "//android.widget.Button[@resource-id='add-to-cart-button']"; }

    protected static String viewCartButtonLocator(){ return "//android.widget.ImageView[@content-desc='Cart']"; }

    protected static String proceedToBuyButtonLocator(){ return "//android.widget.Button[@text='Proceed to Buy']"; }

    protected static String selectedProductTitleLocator(){ return "//android.view.View[@resource-id='title_feature_div']/android.view.View";}


    public void clickOnAddToCart(){
        waitTimer(6000);
        scrollToElementByText("Add to Cart");
        waitForVisibilityOfElement(By.xpath(addToCartButtonLocator()), 5);
        clickElementByXpath(addToCartButtonLocator());

    }

    public void clickToViewCart(){
        clickElementByXpath(viewCartButtonLocator());
        waitForVisibilityOfElement(By.xpath(proceedToBuyButtonLocator()), 15);
    }

    public void clickProceedToBuy() {
        waitTimer(2000);
        clickElementByXpath(proceedToBuyButtonLocator());
    }

}
