package locators;

import base.Base;
import org.openqa.selenium.By;

public class Amazon_Login_Page extends Base {


    private String customerSignInButtonID(){
        return "sign_in_button";
    }

    private String emailTextboxID(){
        return "//android.widget.EditText[@resource-id='ap_email_login']";
    }

    private String continueButtonID(){ return "//android.widget.Button[@resource-id='continue']"; }

    private String passwordButtonID(){
        return "//android.widget.EditText[@resource-id='ap_password']";
    }

    private String submitSignInButtonID(){
        return "//android.widget.Button[@resource-id='signInSubmit']";
    }



    public void clickOnAlreadyCustomer(){
        clickElementByID(customerSignInButtonID());
        waitForElementToBeInvisible(By.id(customerSignInButtonID()), 5);
    }

    public void enterLoginEmailAddress(String email){
        sendKeysToElementByXpath(emailTextboxID(), email);
    }

    public void clickOnContinueButton(){
        clickElementByXpath(continueButtonID());
    }

    public void enterLoginPaasword(String password){
        sendKeysToElementByXpath(passwordButtonID(), password);
    }

    public void clickOnSignInButton(){
        clickElementByXpath(submitSignInButtonID());
    }


}
