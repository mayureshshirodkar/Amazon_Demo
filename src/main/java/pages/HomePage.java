package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends Base {

    private ProductPage product_page = new ProductPage();

    private String saveLanguageChangeLocator(){ return "//android.widget.Button[@text='Save Changes']";}

    private String languageRadioLocator(){ return "//*[@text='English - EN']";}

    private String homeButtonLocator(){ return "//android.widget.ImageView[@content-desc='Home']";}

    private String deleteProductLocator(){ return "//android.widget.Button[@text='Delete']";}



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
        scrollToElementByText("Delete");
        List<WebElement> products = findElementsByXpath(deleteProductLocator());
        for(WebElement product:products){
            product.click();
        }
    }

}
