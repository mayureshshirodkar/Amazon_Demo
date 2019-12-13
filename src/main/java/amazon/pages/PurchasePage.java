package amazon.pages;

import amazon.base.Base;
import amazon.utilities.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PurchasePage extends Base {

    private String useThisAddressLocator = "//*[@resource-id='a-autoid-0-announce']";

    private String preferedTimeSlotLocator = "//*[@text='11:30 - 1:00 PM']";

    private String continueButtonLocator = "//*[@text='Continue']";

    private String paymentOptionLocator(String option){ return "//android.view.View[@text='" + option + "']"; }

    private String chooseBankOptionLocator = "//*[starts-with(@resource-id,'pp-') and ends-with(@resource-id,'-96')]";

    private String selectBankForNetbanking(String bank){ return "//android.view.View[@text='"+ bank +"']"; }

    private String verifyButtonLocator = "//*[@text='Verify']";

    private String placeYourOrderLocator = "//*[@text='Place Your Order and Pay']";

    private static Logger purchasePageLogger = LogManager.getLogger(PurchasePage.class);


    /**
     * Click on the use this address button
     */
    public void clickUseThisAddress(){
        waitTimer(2);
        clickElementByXpath(useThisAddressLocator, purchasePageLogger, "User clicks on use this address button");
        waitTimer(2);
    }

    /**
     * Assert the user is on Place order and pay page
     */
    public void verifyUserOnPlaceOrder(){
        assertIfTrue(placeYourOrderLocator, "Assert if user is on place order page");
    }

    /**
     * Click on the continue button
     */
    public void clickOnContinueButton(){
        scrollToElementByText("Continue");
        clickElementByXpath(continueButtonLocator, purchasePageLogger, "User clicks on continue button");
    }

    /**
     * Click on the prefered time slot
     */
    public void selectPreferedTimeSlot(){
        clickOnContinueButton();
        clickElementByXpath(preferedTimeSlotLocator, purchasePageLogger, "User clicks on prefered time button");
        clickOnContinueButton();
        Utils.passStep(purchasePageLogger, "User selects prefered time slot");
    }

    /**
     * Click on the payment option
     */
    public void clickOnPaymentOption(String option){
        scrollToElementByText(option);
        clickElementByXpath(paymentOptionLocator(option), purchasePageLogger, "User clicks on payment option button");
    }

    /**
     * Click on the bank used for netbanking
     * @param bank - Bank to be used for netbanking
     */
    public void selectNetBankingOption(String bank){
        clickElementByXpath(chooseBankOptionLocator, purchasePageLogger, "User clicks on bank option button");
        clickElementByXpath(selectBankForNetbanking(bank), purchasePageLogger, "User clicks on netbanking option button");
        Utils.passStep(purchasePageLogger, "User selects net banking option");
    }

    /**
     * Click on the vpa to be used for UPI payment
     * @param vpa_value - VPA to be used for UPI
     */
    public void selectUPI(String vpa_value){
        sendKeysToElementByXpath("", vpa_value);
        clickElementByXpath(verifyButtonLocator, purchasePageLogger, "User enters vpa for UPI");
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
        Utils.passStep(purchasePageLogger, "User proceeds after updating payment option");

    }



}
