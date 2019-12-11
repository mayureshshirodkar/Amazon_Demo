package pages;

import base.Base;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends Base {

    private String customerSignInButtonID = "sign_in_button";

    private String emailTextBoxID = "//android.widget.EditText[@resource-id='ap_email_login']";

    private String continueButtonID= "//android.widget.Button[@resource-id='continue']";

    private String passwordButtonID = "//android.widget.EditText[@resource-id='ap_password']";

    private String submitSignInButtonID = "//android.widget.Button[@resource-id='signInSubmit']";


    /**
     * Click on already a customer button
     */
    public void clickOnAlreadyCustomer(){
        clickElementByID(customerSignInButtonID);
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(emailTextBoxID), 15).isDisplayed(),
                "Assertion Failed! User is not navigated to Welcome screen");
    }

    /**
     * Enter user email address
     * @param email email address for login
     */
    public void enterLoginEmailAddress(String email){
        sendKeysToElementByXpath(emailTextBoxID, email);
    }


    /**
     * Click on continue to next page
     */
    public void clickOnContinueButton(){
        clickElementByXpath(continueButtonID);
    }

    /**
     * Enter user password
     * @param password password parameter for login
     */
    public void enterLoginPassword(String password){
        sendKeysToElementByXpath(passwordButtonID, password);
    }


    /**
     * Click on Sign in button to Login to app
     */
    public void clickOnSignInButton(){
        clickElementByXpath(submitSignInButtonID);
        waitForElementToBeInvisible(By.xpath(submitSignInButtonID), 5);
    }

    /**
     * Enter Username and perform click on Continue button
     */
    public void enterUserName(String email){
        enterLoginEmailAddress(email);
        clickOnContinueButton();
    }


    /**
     * Assert to check if Login is successful
     */
    public void verifySuccessfulLogin(){
        Assert.assertTrue(waitForVisibilityOfElement(By.xpath(ProductPage.viewCartButtonLocator()), 5).isDisplayed(),
                "Assertion Failed! user has failed to log in");
        System.out.println("Customer Logged in !!");
    }

}
