package locators;

import base.Base;
import org.openqa.selenium.By;

public class Amazon_Product_Page extends Base {

    private String addToCartButtonLocator(){ return "//android.widget.Button[@resource-id='add-to-cart-button']"; }

    private String viewCartButtonLocator(){ return "//android.widget.ImageView[@content-desc='Cart']"; }

    private String proceedToBuyButtonLocator(){ return "//android.widget.Button[@text='Proceed to Buy']"; }


    public void clickOnAddToCart(){
        waitTimer(2000);
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
