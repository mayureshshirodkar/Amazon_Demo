package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class HomePage extends Base {

    private ProductPage product_page = new ProductPage();

    private String saveLanguageChangeLocator(){ return "//android.widget.Button[@text='Save Changes']";}

    private String languageRadioLocator(){ return "//*[@text='English - EN']";}

    private String homeButtonLocator(){ return "//android.widget.ImageView[@content-desc='Home']";}

    private String deleteProductLocator(){ return "//android.widget.Button[@text='Delete']";}

    private String emptyCartMessageLocator(){ return "//android.view.View[contains(@text,'Your Shopping Cart is empty.')]";}



    public void acceptLanguagePopup(){
        if(waitForVisibilityOfElement(By.xpath(languageRadioLocator()), 15) != null) {
            clickElementByXpath(languageRadioLocator());
            clickElementByXpath(saveLanguageChangeLocator());
        }
    }

    public void navigateToHome(){
        clickElementByXpath(homeButtonLocator());
    }

    public void deleteProductsAddedToCart(){
        product_page.clickToViewCart();
        scroll("down");
        List<WebElement> products = findElementsByXpath(deleteProductLocator());
        for(WebElement product:products){
            product.click();
        }
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(emptyCartMessageLocator()), 10).isDisplayed(),
                "Assertion Failed! Payment option not shown");
    }

}
