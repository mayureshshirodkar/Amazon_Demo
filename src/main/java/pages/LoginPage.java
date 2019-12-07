package pages;

import base.Base;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends Base {

    private String customerSignInButtonID(){
        return "sign_in_button";
    }

    private String emailTextBoxID(){
        return "//android.widget.EditText[@resource-id='ap_email_login']";
    }

    private String continueButtonID(){ return "//android.widget.Button[@resource-id='continue']"; }

    private String passwordButtonID(){
        return "//android.widget.EditText[@resource-id='ap_password']";
    }

    private String submitSignInButtonID(){
        return "//android.widget.Button[@resource-id='signInSubmit']";
    }


    /**
     * Click on already a customer button
     */
    public void clickOnAlreadyCustomer(){
        clickElementByID(customerSignInButtonID());
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(emailTextBoxID()), 15).isDisplayed(),
                "Assertion Failed! User is not navigated to Welcome screen");
    }

    /**
     * Enter user email address
     */
    public void enterLoginEmailAddress(String email){
        sendKeysToElementByXpath(emailTextBoxID(), email);
    }


    /**
     * Click on continue to next page
     */
    public void clickOnContinueButton(){
        clickElementByXpath(continueButtonID());
    }

    /**
     * Enter user password
     * @param password
     */
    public void enterLoginPassword(String password){
        sendKeysToElementByXpath(passwordButtonID(), password);
    }


    public void clickOnSignInButton(){
        clickElementByXpath(submitSignInButtonID());
        waitForElementToBeInvisible(By.xpath(submitSignInButtonID()), 5);
    }

    public void enterUserName(String email){
        enterLoginEmailAddress(email);
        clickOnContinueButton();
    }

    public void verifySuccessfulLogin(){
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(ProductPage.viewCartButtonLocator()), 5).isDisplayed(),
                "Assertion Failed! user has failed to log in");
    }

}
