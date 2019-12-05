package locators;

import base.Base;
import org.openqa.selenium.By;

public class Amazon_Purchase_Page extends Base {

    private String useThisAdressLocator(){ return "//android.widget.Button[@resource-id='a-autoid-0-announce']"; }

    private String preferedTimeSlotLocator(){return "//android.view.View[@text='10:00 - 11:30 AM']";}

    private String continueButtonLocator(){ return "//android.widget.Button[@text='Continue']"; }

    private String paymentOptionLocator(String option){ return "//android.view.View[@text='" + option + "']"; }

    private String chooseBankOptionLocator(){ return "//android.view.View[starts-with(@resource-id,'pp-') and ends-with(@resource-id,'-96')]"; }

    private String selectBankForNetbanking(String bank){ return "//android.view.View[@text='"+ bank +"']"; }

    private String placeYourOrderLocator(){ return "//android.widget.Button[@text='Place Your Order and Pay']"; }


    public void clickUseThisAddress(){
        waitTimer(2000);
        clickElementByXpath(useThisAdressLocator());
        waitTimer(2000);
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
