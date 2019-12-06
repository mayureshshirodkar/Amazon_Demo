package pages;

import base.Base;
import org.openqa.selenium.By;


public class PurchasePage extends Base {

    private String useThisAddressLocator(){ return "//android.widget.Button[@resource-id='a-autoid-0-announce']"; }

    private String preferedTimeSlotLocator(){return "//android.view.View[@text='10:00 - 11:30 AM']";}

    private String continueButtonLocator(){ return "//android.widget.Button[@text='Continue']"; }

    private String paymentOptionLocator(String option){ return "//android.view.View[@text='" + option + "']"; }

    private String chooseBankOptionLocator(){ return "//android.view.View[starts-with(@resource-id,'pp-') and ends-with(@resource-id,'-96')]"; }

    private String selectBankForNetbanking(String bank){ return "//android.view.View[@text='"+ bank +"']"; }

    private String verifyButtonLocator(){ return "//android.widget.Button[@text='Verify']"; }

    private String placeYourOrderLocator(){ return "//android.widget.Button[@text='Place Your Order and Pay']"; }


    public void clickUseThisAddress(){
        waitTimer(2000);
        clickElementByXpath(useThisAddressLocator());
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
    }

    public void selectUPI(String vpa_value){
        sendKeysToElementByXpath("", vpa_value);
        clickElementByXpath(verifyButtonLocator());
    }

    public void selectPaymentAndProceed(String payment_option){

        String[] options = payment_option.split(" - ");
        String payment_method = options[0];
        String payment_method_detail = options[1];

        clickOnPaymentOption(payment_method);

        if(payment_method.equalsIgnoreCase("Net Banking"))
            selectNetBankingOption(payment_method_detail);

        else if(payment_method.equalsIgnoreCase("UPI"))
            selectUPI(payment_method_detail);


        clickOnContinueButton();
        waitForVisibilityOfElement(By.xpath(placeYourOrderLocator()), 10);


    }

}
