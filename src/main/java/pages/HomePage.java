package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomePage extends Base {

    private ProductPage product_page = new ProductPage();

    private String saveLanguageChangeLocator = "//android.widget.Button[@text='Save Changes']";

    private String languageRadioLocator = "//*[@text='English - EN']";

    private String homeButtonLocator = "//android.widget.ImageView[@content-desc='Home']";

    private String deleteProductLocator = "//android.widget.Button[@text='Delete']";

    private String emptyCartMessageLocator = "//android.view.View[contains(@text,'Your Shopping Cart is empty.')]";


    /**
     * Accept the app language selection popup
     */
    public void acceptLanguagePopup(){
        if(waitForVisibilityOfElement(By.xpath(languageRadioLocator), 15) != null) {
            clickElementByXpath(languageRadioLocator);
            clickElementByXpath(saveLanguageChangeLocator);
        }
    }

    /**
     * Tap on home and Navigate to Home page
     */
    public void navigateToHome(){
        clickElementByXpath(homeButtonLocator);
    }


    /**
     * Delete all products added to cart
     */
    public void deleteProductsAddedToCart(){
        product_page.clickToViewCart();
        swipeInDirection("down");
        List<WebElement> products = findElementsByXpath(deleteProductLocator);
        for(WebElement product:products){
            product.click();
        }
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(emptyCartMessageLocator), 10).isDisplayed(),
                "Assertion Failed! Payment option not shown");
        System.out.println("Products removed from cart!!");
    }

}
