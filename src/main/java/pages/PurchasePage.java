package pages;

import base.Base;
import org.openqa.selenium.By;
import org.testng.Assert;


public class PurchasePage extends Base {

    private String useThisAddressLocator(){ return "//android.widget.Button[@resource-id='a-autoid-0-announce']"; }

    private String preferedTimeSlotLocator(){return "//android.view.View[@text='11:30 - 1:00 PM']";}

    private String continueButtonLocator(){ return "//android.widget.Button[@text='Continue']"; }

    private String paymentOptionLocator(String option){ return "//android.view.View[@text='" + option + "']"; }

    private String chooseBankOptionLocator(){ return "//android.view.View[starts-with(@resource-id,'pp-') and ends-with(@resource-id,'-96')]"; }

    private String selectBankForNetbanking(String bank){ return "//android.view.View[@text='"+ bank +"']"; }

    private String verifyButtonLocator(){ return "//android.widget.Button[@text='Verify']"; }

    private String placeYourOrderLocator(){ return "//android.widget.Button[@text='Place Your Order and Pay']"; }


    /**
     * Click on the use this address button
     */
    public void clickUseThisAddress(){
        waitTimer(2);
        clickElementByXpath(useThisAddressLocator());
        waitTimer(2);
    }

    /**
     * Assert the user is on Place order and pay page
     */
    public void verifyUserOnPlaceOrder(){
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(placeYourOrderLocator()), 10).isDisplayed(),
                "Assertion Failed! Payment option not shown");
        System.out.println("Order placed successfully!!");
    }

    /**
     * Click on the continue button
     */
    public void clickOnContinueButton(){
        scrollToElementByText("Continue");
        clickElementByXpath(continueButtonLocator());
    }

    /**
     * Click on the prefered time slot
     */
    public void selectPreferedTimeSlot(){
        clickOnContinueButton();
        clickElementByXpath(preferedTimeSlotLocator());
        clickOnContinueButton();
    }

    /**
     * Click on the payment option
     */
    public void clickOnPaymentOption(String option){
        scrollToElementByText(option);
        clickElementByXpath(paymentOptionLocator(option));
    }

    /**
     * Click on the bank used for netbanking
     * @param bank - Bank to be used for netbanking
     */
    public void selectNetBankingOption(String bank){
        clickElementByXpath(chooseBankOptionLocator());
        clickElementByXpath(selectBankForNetbanking(bank));
    }

    /**
     * Click on the vpa to be used for UPI payment
     * @param vpa_value - VPA to be used for UPI
     */
    public void selectUPI(String vpa_value){
        sendKeysToElementByXpath("", vpa_value);
        clickElementByXpath(verifyButtonLocator());
    }

    /**
     * Select the payment option and complete payment
     * @param payment_option - Payment options
     */
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

    }



}
