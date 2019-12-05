package locators;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Amazon_Search_Page extends Base {

    private String itemSearchTextBoxID(){ return "rs_search_src_text"; }

    private String saveLanguageChangeLocator(){ return "//android.widget.Button[@text='Save Changes']";}

    private String languageRadioLocator(){ return "//*[@text='English - EN']";}

    private String searchResultXpath(String value){ return "//android.widget.TextView[@text='" + value + "']"; }

    private String addToCartButtonLocator(){ return "//android.widget.Button[@resource-id='add-to-cart-button']"; }

    private String viewCartButtonLocator(){ return "//android.widget.ImageView[@content-desc='Cart']"; }

    private String proceedToBuyButtonLocator(){ return "//android.widget.Button[@text='Proceed to Buy']"; }

    private String useThisAdressLocator(){ return "//android.widget.Button[@resource-id='a-autoid-0-announce']"; }

    private String preferedTimeSlotLocator(){return "//android.view.View[@text='10:00 - 11:30 AM']";}

    private String continueButtonLocator(){ return "//android.widget.Button[@text='Continue']"; }

    private String paymentOptionLocator(String option){ return "//android.view.View[@text='" + option + "']"; }

    private String chooseBankOptionLocator(){ return "//android.view.View[starts-with(@resource-id,'pp-') and ends-with(@resource-id,'-96')]"; }

    private String selectBankForNetbanking(String bank){ return "//android.view.View[@text='"+ bank +"']"; }

    private String placeYourOrderLocator(){ return "//android.widget.Button[@text='Place Your Order and Pay']"; }


    public void clickOnSearchTextBox(){
        clickElementByID(itemSearchTextBoxID());
    }

    public void acceptLanguagePopup(){
        if(waitForVisibilityOfElement(By.xpath(languageRadioLocator()), 15) != null) {
            clickElementByXpath(languageRadioLocator());
            clickElementByXpath(saveLanguageChangeLocator());
        }
    }

    public void enterTextInSearchTextBox(String value){
        sendKeysToElementByID(itemSearchTextBoxID(), value);
    }


    public void selectFromSearchOption(String value){
        clickElementByXpath(searchResultXpath(value));
    }

    public void selectItemFromList(){
        List<WebElement> element = findElementsByID("item_title");
        element.get(1).click();
        waitTimer(2000);
    }

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

    public void clickProceedToBuy(){
        clickElementByXpath(proceedToBuyButtonLocator());
    }

    public void clickUseThisAddress(){
        clickElementByXpath(useThisAdressLocator());
    }

    public void clickOnContinueButton(){
        scrollToElementByText("Continue");
        clickElementByXpath(continueButtonLocator());
    }

    public void selectPreferedTimeSlot(){
        clickOnContinueButton();
        clickElementByXpath(preferedTimeSlotLocator());
        clickOnContinueButton();
    }

    public void clickOnPaymentOption(String option){
        scrollToElementByText(option);
        clickElementByXpath(paymentOptionLocator(option));
    }

    public void selectNetBankingOption(String bank){
        clickElementByXpath(chooseBankOptionLocator());
        clickElementByXpath(selectBankForNetbanking(bank));
        clickOnContinueButton();
        waitForVisibilityOfElement(By.xpath(placeYourOrderLocator()), 10);
    }




}
