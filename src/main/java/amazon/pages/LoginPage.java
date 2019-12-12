package amazon.pages;

import amazon.base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import amazon.utilities.Utils;

public class LoginPage extends Base {

    private String customerSignInButtonID = "sign_in_button";

    private String emailTextBoxID = "//*[@resource-id='ap_email_login']";

    private String continueButtonID= "//*[@resource-id='continue']";

    private String passwordButtonID = "//*[@resource-id='ap_password']";

    private String submitSignInButtonID = "//*[@resource-id='signInSubmit']";

    private static Logger loginPageLogger = LogManager.getLogger(LoginPage.class);

    /**
     * Click on already a customer button
     */
    public void clickOnAlreadyCustomer(){
        clickElementByID(customerSignInButtonID);
        assertIfTrue(emailTextBoxID, "Verify is user is navigated to Welcome screen");
        Utils.infoLog(loginPageLogger,"User navigated to Welcome screen !!");
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
        Utils.infoLog(loginPageLogger, "User enters password");
    }


    /**
     * Click on Sign in button to Login to app
     */
    public void clickOnSignInButton(){
        clickElementByXpath(submitSignInButtonID);
        waitForElementToBeInvisible(By.xpath(submitSignInButtonID), 5);
        Utils.infoLog(loginPageLogger, "User tried to sign in");
    }

    /**
     * Enter Username and perform click on Continue button
     */
    public void enterUserName(String email){
        enterLoginEmailAddress(email);
        clickOnContinueButton();
        Utils.infoLog(loginPageLogger,"User enters email address and continues to next page");
    }


    /**
     * Assert to check if Login is successful
     */
    public void verifySuccessfulLogin(){
        assertIfTrue(ProductPage.viewCartButtonLocator, "Verify user has logged in successfully");
        Utils.infoLog(loginPageLogger,"Customer Logged in !!");
    }

}
